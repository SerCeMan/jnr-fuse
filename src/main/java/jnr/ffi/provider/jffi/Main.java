package jnr.ffi.provider.jffi;

import jnr.ffi.LibraryLoader;
import jnr.ffi.Pointer;
import jnr.ffi.Runtime;
import jnr.ffi.mapper.CompositeTypeMapper;
import jnr.ffi.mapper.DefaultSignatureType;
import jnr.ffi.mapper.FromNativeContext;
import jnr.ffi.mapper.FromNativeConverter;
import jnr.ffi.provider.ClosureManager;
import ru.serce.jfuse.FileStat;
import ru.serce.jfuse.FuseFillDir;
import ru.serce.jfuse.FuseOperations;
import ru.serce.jfuse.LibFuse;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Collections;
import java.util.Objects;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ThreadLocalRandom;

public class Main {
    public static final String HELLO_PATH = "/hello";
    public static final String HELLO_STR = "Hello World!";

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

        fuseOperations.getattr.set((path, stbuf) -> {
            System.out.println("GETATTR");
            FileStat stat = new FileStat(Runtime.getSystemRuntime());
            stat.useMemory(stbuf);
            int res = 0;
            if (Objects.equals(path, "/")) {
                stat.st_mode.set(jnr.posix.FileStat.S_IFDIR | 0755);
                stat.st_nlink.set(2);
            } else if ("/hello".equals(path)) {
                stat.st_mode.set(jnr.posix.FileStat.S_IFREG | 0444);
                stat.st_nlink.set(1);
                stat.st_size.set("Hello world".length());
            } else {
                res = -2;
            }
            System.out.println(path + " RES=" + res);
            return res;
        });
        fuseOperations.readdir.set((path, buf, filter, offset, fi) -> {
            try {
                System.out.println("READIR");
                if (!"/".equals(path)) {
                    return -2;
                }
                SimpleNativeContext ctx = new SimpleNativeContext(Runtime.getSystemRuntime(), Collections.emptyList());
                FromNativeConverter<FuseFillDir, Pointer> instance = (FromNativeConverter<FuseFillDir, Pointer>) ClosureFromNativeConverter.
                        getInstance(Runtime.getSystemRuntime(), DefaultSignatureType.create(FuseFillDir.class, (FromNativeContext) ctx), classLoader, ctm);
                System.out.println("WOW!");

                FuseFillDir fuseFillDir =
                        instance.fromNative(filter,
                                ctx);
                fuseFillDir.fuseFillDir(buf, ".", null, 0);
                fuseFillDir.fuseFillDir(buf, "..", null, 0);
                fuseFillDir.fuseFillDir(buf, HELLO_PATH.substring(1), null, 0);
                System.out.println(path);
                return 0;
            } catch (Exception e) {
                e.printStackTrace();
                return 0;
            }
        });
        fuseOperations.open.set((path, fi) -> {
            System.out.println("OPEN");
            System.out.println(path);
            if (!HELLO_PATH.equals(path)) {
                return -2;
            }
            return 0;
        });
        fuseOperations.read.set((path, buf, size, offset, fi) -> {
            System.out.println("READ");
            System.out.println(path);
            System.out.println("SIZE  =" + size);
            System.out.println("OFFSET=" + offset);

//                size -= Math.pow(2, 31);
//                offset -= Math.pow(2, 31);

            if (!HELLO_PATH.equals(path)) {
                return -2;
            }

            byte[] bytes = HELLO_STR.getBytes();
            int length = bytes.length;
            if (offset < length) {
                if (offset + size > length) {
                    size = length - offset;
                }
                buf.put(0, bytes, 0, bytes.length);
            } else {
                size = 0;
            }
            return (int) size;
        });
        fuseOperations.readlink.set((path, buf, size) -> 0);
        fuseOperations.mknod.set((path, mode, rdev) -> 0);
        fuseOperations.mkdir.set((path, mode) -> 0);
        fuseOperations.unlink.set(path -> 0);
        fuseOperations.rmdir.set(path -> 0);
        fuseOperations.symlink.set((oldpath, newpath) -> 0);

        try {
            new ProcessBuilder("fusermount", "-u", "/tmp/mnt").start();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String[] arg = new String[]{"ARG" + ThreadLocalRandom.current().nextInt(), "-f", "-d", "/tmp/mnt"};
        try {
            ForkJoinPool.commonPool().submit(() -> {
                System.out.println("START");
                int res = fuse.fuse_main_real(arg.length, arg, fuseOperations, 360, null);
                System.out.println("RESULT = " + res);
                System.out.println("END");
            });
            Thread.sleep(1000000L);
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }
}
