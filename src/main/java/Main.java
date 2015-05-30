import jnr.ffi.Pointer;
import jnr.ffi.Struct;
import jnr.ffi.types.off_t;
import jnr.ffi.types.size_t;
import ru.serce.jnrfuse.ErrorCodes;
import ru.serce.jnrfuse.FuseFillDir;
import ru.serce.jnrfuse.FuseStubFS;
import ru.serce.jnrfuse.struct.FileStat;
import ru.serce.jnrfuse.struct.FuseFileInfo;
import ru.serce.jnrfuse.struct.FuseOperations;
import ru.serce.jnrfuse.struct.Statvfs;

import java.lang.Runtime;
import java.nio.file.Paths;
import java.util.Objects;

public class Main {
    public static final String HELLO_PATH = "/hello";
    public static final String HELLO_STR = "Hello World!";

    public static void main(String[] args) throws InterruptedException, IllegalAccessException, NoSuchFieldException {
        FuseStubFS stub = new FuseStubFS() {

            @Override
            public int getattr(String path, FileStat stat) {
                System.out.println("GETATTR");
                int res = 0;
                if (Objects.equals(path, "/")) {
                    stat.st_mode.set(jnr.posix.FileStat.S_IFDIR | 0755);
                    stat.st_nlink.set(2);
                } else if ("/hello".equals(path)) {
                    stat.st_mode.set(jnr.posix.FileStat.S_IFREG | 0444);
                    stat.st_nlink.set(1);
                    stat.st_size.set(HELLO_STR.getBytes().length);
                    stat.st_atime.set(System.currentTimeMillis());
                } else {
                    res = -ErrorCodes.ENOENT();
                }
                System.out.println(path + " RES=" + res);
                return res;
            }

            @Override
            public int readdir(String path, Pointer buf, FuseFillDir filter, @off_t long offset, FuseFileInfo fi) {
                try {
                    System.out.println("READIR");
                    if (!"/".equals(path)) {
                        return -2;
                    }
                    System.out.println("WOW!");

                    filter.apply(buf, ".", null, 0);
                    filter.apply(buf, "..", null, 0);
                    filter.apply(buf, HELLO_PATH.substring(1), null, 0);
                    System.out.println(path);
                    return 0;
                } catch (Exception e) {
                    e.printStackTrace();
                    return 0;
                }
            }

            @Override
            public int open(String path, FuseFileInfo fi) {
                System.out.println("OPEN");
                System.out.println(path);
                if (!HELLO_PATH.equals(path)) {
                    return -2;
                }
                return 0;
            }

            @Override
            public int read(String path, Pointer buf, @size_t long size, @off_t long offset, FuseFileInfo fi) {
                System.out.println("READ");
                System.out.println(path);
                System.out.println("SIZE  =" + size);
                System.out.println("OFFSET=" + offset);

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
            }
        };

        try {
            System.out.println(Struct.size(new Statvfs(jnr.ffi.Runtime.getSystemRuntime())));
            stub.mount(Paths.get("/tmp/mnt"));
            Thread.sleep(100000000L);
        } finally {
            stub.umount();
        }
    }
}
