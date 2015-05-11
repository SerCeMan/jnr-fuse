package jnr.ffi.provider.jffi;

import com.google.common.primitives.UnsignedLong;
import com.google.common.primitives.UnsignedLongs;
import com.kenai.jffi.Closure;
import jnr.ffi.LibraryLoader;
import jnr.ffi.Pointer;
import jnr.ffi.Runtime;
import jnr.ffi.Struct;
import jnr.ffi.mapper.CompositeTypeMapper;
import jnr.ffi.mapper.FromNativeConverter;
import jnr.ffi.mapper.SignatureType;
import jnr.ffi.mapper.SignatureTypeMapper;
import jnr.ffi.provider.ClosureManager;
import jnr.ffi.provider.SigType;
import jnr.ffi.provider.jffi.ClosureFromNativeConverter;
import jnr.ffi.provider.jffi.SimpleNativeContext;
import ru.serce.jfuse.FileStat;
import ru.serce.jfuse.FuseFillDir;
import ru.serce.jfuse.FuseOperations;
import ru.serce.jfuse.LibFuse;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ThreadLocalRandom;

public class Main {
    public static final String HELLO_PATH = "/hello";

    public static void main(String[] args) throws InterruptedException, IllegalAccessException, NoSuchFieldException {
        LibraryLoader<LibFuse> loader = LibraryLoader.create(LibFuse.class);
        LibFuse fuse = loader.load("libfuse.so.2");

        ClosureManager closureManager = Runtime.getSystemRuntime().getClosureManager();
        Field classLoaderField = NativeClosureManager.class.getDeclaredField("classLoader");
        classLoaderField.setAccessible(true);

        AsmClassLoader classLoader = (AsmClassLoader) classLoaderField.get(closureManager);

        Field typeMapperField = NativeClosureManager.class.getDeclaredField("typeMapper");
        typeMapperField.setAccessible(true);
        CompositeTypeMapper ctm = (CompositeTypeMapper) typeMapperField.get(closureManager);

        FuseOperations fuseOperations = new FuseOperations(Runtime.getSystemRuntime());
        fuseOperations.setGetAttr((path, stbuf) -> {
            System.out.println("GETATTR");
            FileStat stat = new FileStat(Runtime.getSystemRuntime());
            stat.useMemory(stbuf);
            if (Objects.equals(path, "/")) {
                stat.st_mode.set(jnr.posix.FileStat.S_IFDIR | 0755);
                stat.st_nlink.set(2);
                return 0;
            } else if ("/hello".equals(path)) {
                stat.st_mode.set(jnr.posix.FileStat.S_IFREG | 0444);
                stat.st_nlink.set(1);
                stat.st_size.set("Hello world".length());
                return 0;
            }
            System.out.println(path);
            return -2;
        });
        fuseOperations.setReaddir((path, buf, filter, offset, fi) -> {
            System.out.println("READIR");
            if (! "/".equals(path)) {
                return -2;
            }
            FromNativeConverter<FuseFillDir, Pointer> instance = (FromNativeConverter<FuseFillDir, Pointer>) ClosureFromNativeConverter.getInstance(Runtime.getSystemRuntime(), new SignatureType() {
                @Override
                public Class getDeclaredType() {
                    return FuseFillDir.class;
                }

                @Override
                public Collection<Annotation> getAnnotations() {
                    return null;
                }

                @Override
                public Type getGenericType() {
                    return null;
                }
            }, classLoader, ctm);

            FuseFillDir fuseFillDir =
                    instance.fromNative(filter,
                            new SimpleNativeContext(Runtime.getSystemRuntime(), Collections.emptyList()));
            fuseFillDir.fuseFillDir(buf, ".", null, 0);
            fuseFillDir.fuseFillDir(buf, "..", null, 0);
            fuseFillDir.fuseFillDir(buf, HELLO_PATH, null, 0);
            System.out.println(path);
            return 0;
        });
        fuseOperations.setOpen(new FuseOperations.OpenCallback() {
            @Override
            public int open(String path, Pointer fi) {
                System.out.println("OPEN");
                System.out.println(path);
                if(!HELLO_PATH.equals(path)) {
                    return -2;
                }
                return 0;
            }
        });
        fuseOperations.setRead((path, buf, size, offset, fi) -> {
            System.out.println("READ");
            System.out.println(path);

//                size -= Math.pow(2, 31);
//                offset -= Math.pow(2, 31);

            if(!"/".equals(path)) {
                return -2;
            }

            int length = HELLO_PATH.length();
            if(offset < length) {
                if(offset + size > length) {
                    size = length - offset;
                }
                byte[] bytes = HELLO_PATH.getBytes();
                buf.put(0, bytes, 0, bytes.length);
            } else {
                size = 0;
            }
            return (int) size;
        });

        try {
            new ProcessBuilder("fusermount", "-u", "/tmp/mnt").start();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String[] arg = new String[]{"ARG" + ThreadLocalRandom.current().nextInt(), "-f", "-d", "/tmp/mnt"};
        try {
            ForkJoinPool.commonPool().submit(() -> {
                System.out.println("START");
                int res = fuse.fuse_main_real(arg.length, arg, fuseOperations, 200, null);
                System.out.println("RESULT = " + res);
                System.out.println("END");
            });
            Thread.sleep(1000000L);
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }
}
