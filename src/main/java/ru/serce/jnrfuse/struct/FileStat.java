package ru.serce.jnrfuse.struct;

import jnr.ffi.Runtime;
import jnr.ffi.Struct;
import jnr.ffi.StructLayout;
import jnr.posix.Times;

import static jnr.posix.util.Platform.IS_32_BIT;
import static jnr.posix.util.Platform.IS_64_BIT;

/**
 * stat data structure
 *
 * @author Sergey Tselovalnikov
 * @since 31.05.15
 */
public class FileStat extends Struct {
    public static final int S_IFIFO = 0010000;  // named pipe (fifo)
    public static final int S_IFCHR = 0020000;  // character special
    public static final int S_IFDIR = 0040000;  // directory
    public static final int S_IFBLK = 0060000;  // block special
    public static final int S_IFREG = 0100000;  // regular
    public static final int S_IFLNK = 0120000;  // symbolic link
    public static final int S_IFSOCK = 0140000; // socket
    public static final int S_IFMT = 0170000;   // file mask for type checks
    public static final int S_ISUID = 0004000;  // set user id on execution
    public static final int S_ISGID = 0002000;  // set group id on execution
    public static final int S_ISVTX = 0001000;  // save swapped text even after use
    public static final int S_IRUSR = 0000400;  // read permission, owner
    public static final int S_IWUSR = 0000200;  // write permission, owner
    public static final int S_IXUSR = 0000100;  // execute/search permission, owner
    public static final int S_IRGRP = 0000040;  // read permission, group
    public static final int S_IWGRP = 0000020;  // write permission, group
    public static final int S_IXGRP = 0000010;  // execute/search permission, group
    public static final int S_IROTH = 0000004;  // read permission, other
    public static final int S_IWOTH = 0000002;  // write permission, other
    public static final int S_IXOTH = 0000001;  // execute permission, other

    public static final int ALL_READ = S_IRUSR | S_IRGRP | S_IROTH;
    public static final int ALL_WRITE = S_IWUSR | S_IWGRP | S_IWOTH;
    public static final int S_IXUGO = S_IXUSR | S_IXGRP | S_IXOTH;

    public final dev_t st_dev;      /* Device.  */
    private final Unsigned16 pad1;
    public final UnsignedLong st_ino;         /* File serial number.	*/
    public final nlink_t st_nlink;     /* Link count.  */
    public final mode_t st_mode;       /* File mode.  */
    public final uid_t st_uid;      /* User ID of the file's owner.	*/
    public final gid_t st_gid;      /* Group ID of the file's group.*/
    public final dev_t st_rdev;     /* Device number, if device.  */
    private final Unsigned16 pad2;
    public final SignedLong st_size;        /* Size of file, in bytes.  */
    public final blkcnt_t st_blksize;  /* Optimal block size for I/O.  */
    public final blkcnt_t st_blocks;   /* Number 512-byte blocks allocated. */
    public final Timespec st_atim;     /* Time of last access.  */
    public final Timespec st_mtim;     /* Time of last modification.  */
    public final Timespec st_ctim;     /* Time of last status change.  */

    public final Signed64 __unused4;
    public final Signed64 __unused5;
    public final Signed64 __unused6;

    public FileStat(Runtime runtime) {
        super(runtime);
        st_dev = new dev_t();
        pad1 = IS_32_BIT ? new Unsigned16() : null;
        st_ino = new UnsignedLong();
        if (IS_32_BIT) {
            st_mode = new mode_t();
            st_nlink = new nlink_t();
        } else {
            st_nlink = new nlink_t();
            st_mode = new mode_t();
        }
        st_uid = new uid_t();
        st_gid = new gid_t();
        st_rdev = new dev_t();
        pad2 = IS_32_BIT ? new Unsigned16() : null;
        st_size = new SignedLong();
        st_blksize = new blkcnt_t();
        st_blocks = new blkcnt_t();
        st_atim = inner(new Timespec(getRuntime()));
        st_mtim = inner(new Timespec(getRuntime()));
        st_ctim = inner(new Timespec(getRuntime()));
        __unused4 = IS_64_BIT ? new Signed64() : null;
        __unused5 = IS_64_BIT ? new Signed64() : null;
        __unused6 = new Signed64();
    }

    public static boolean S_ISTYPE(int mode, int mask) {
        return (mode & S_IFMT) == mask;
    }

    public static boolean S_ISDIR(int mode) {
        return S_ISTYPE(mode, S_IFDIR);
    }

    public static boolean S_ISCHR(int mode) {
        return S_ISTYPE(mode, S_IFCHR);
    }

    public static boolean S_ISBLK(int mode) {
        return S_ISTYPE(mode, S_IFBLK);
    }

    public static boolean S_ISREG(int mode) {
        return S_ISTYPE(mode, S_IFREG);
    }

    public static boolean S_ISFIFO(int mode) {
        return S_ISTYPE(mode, S_IFIFO);
    }

    public static boolean S_ISLNK(int mode) {
        return S_ISTYPE(mode, S_IFLNK);
    }

    public static FileStat of(jnr.ffi.Pointer memory) {
        FileStat stat = new FileStat(Runtime.getSystemRuntime());
        stat.useMemory(memory);
        return stat;
    }
}
