package ru.serce.jnrfuse.struct;

import jnr.ffi.Runtime;
import jnr.ffi.Struct;
import jnr.posix.util.Platform;

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

    public FileStat(Runtime runtime) {
        super(runtime);
        if(Platform.IS_MAC) {
// typedef __uint64_t	__darwin_ino64_t;	/* [???] Used for 64 bit inodes */
// #define __DARWIN_STRUCT_STAT64 { \
//                dev_t		st_dev;			/* [XSI] ID of device containing file */ \
//                mode_t		st_mode;		/* [XSI] Mode of file (see below) */ \
//                nlink_t		st_nlink;		/* [XSI] Number of hard links */ \
//                __darwin_ino64_t st_ino;		/* [XSI] File serial number */ \
//                uid_t		st_uid;			/* [XSI] User ID of the file */ \
//                gid_t		st_gid;			/* [XSI] Group ID of the file */ \
//                dev_t		st_rdev;		/* [XSI] Device ID */ \
//                __DARWIN_STRUCT_STAT64_TIMES \
//                off_t		st_size;		/* [XSI] file size, in bytes */ \
//                blkcnt_t	st_blocks;		/* [XSI] blocks allocated for file */ \
//                blksize_t	st_blksize;		/* [XSI] optimal blocksize for I/O */ \
//                __uint32_t	st_flags;		/* user defined flags for file */ \
//                __uint32_t	st_gen;			/* file generation number */ \
//                __int32_t	st_lspare;		/* RESERVED: DO NOT USE! */ \
//                __int64_t	st_qspare[2];		/* RESERVED: DO NOT USE! */ \
//            }
// #define __DARWIN_STRUCT_STAT64_TIMES \
//              struct timespec st_atimespec;		/* time of last access */ \
//              struct timespec st_mtimespec;		/* time of last data modification */ \
//              struct timespec st_ctimespec;		/* time of last status change */ \
//              struct timespec st_birthtime;	/* time of file creation(birth) */
            st_dev = new dev_t();
            st_mode = new mode_t();
            st_nlink = new nlink_t();
            st_ino = new u_int64_t();
            st_uid = new uid_t();
            st_gid = new gid_t();
            st_rdev = new dev_t();
            st_atim = inner(new Timespec(getRuntime()));
            st_mtim = inner(new Timespec(getRuntime()));
            st_ctim = inner(new Timespec(getRuntime()));
            st_birthtime = inner(new Timespec(getRuntime()));
            st_size = new off_t();
            st_blocks = new blkcnt_t();
            st_blksize = new blksize_t();
            st_flags = new u_int32_t();
            st_gen = new u_int32_t();
            new int32_t();
            new int64_t();
            new int64_t();

            //graveyard
            pad1 = null;
            pad2 = null;
            __pad1 = null;
            __pad2 = null;
            __unused4 = null;
            __unused5 = null;
            __unused6 = null;
        } else if (Platform.IS_WINDOWS) {
            st_dev = new dev_t();
            st_ino = new u_int64_t();
            st_mode = new Signed32();
            st_nlink = new Signed16();
            st_uid = new uid_t();
            st_gid = new gid_t();
            st_rdev = new dev_t();
            st_size = new off_t();
            st_atim = inner(new Timespec(getRuntime()));
            st_mtim = inner(new Timespec(getRuntime()));
            st_ctim = inner(new Timespec(getRuntime()));
            st_blksize = new blksize_t();
            st_blocks = new Signed64();
            st_birthtime = inner(new Timespec(getRuntime()));

            // graveyard
            pad1 = null;
            pad2 = null;
            __pad1 = null;
            __pad2 = null;
            __unused4 = null;
            __unused5 = null;
            __unused6 = null;
            st_flags = null;
            st_gen = null;
        } else if (Platform.IS_LINUX && "aarch64".equals(Platform.ARCH)) {
            st_dev = new dev_t();
            st_ino = new ino_t();
            st_mode = new mode_t();
            st_nlink = new nlink_t();
            st_uid = new uid_t();
            st_gid = new gid_t();
            st_rdev = new dev_t();
            __pad1 = new Unsigned64();
            st_size = new off_t();
            st_blksize = new blksize_t();
            __pad2 = new Unsigned32();
            st_blocks = new blkcnt_t();
            st_atim = inner(new Timespec(getRuntime()));
            st_mtim = inner(new Timespec(getRuntime()));
            st_ctim = inner(new Timespec(getRuntime()));
            __unused4 = new Signed64();

            //graveyard
            pad1 = null;
            pad2 = null;
            st_birthtime = null;
            st_flags = null;
            st_gen = null;
            __unused5 = null;
            __unused6 = null;
        } else {
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
            st_blksize = new blksize_t();
            st_blocks = new blkcnt_t();
            st_atim = inner(new Timespec(getRuntime()));
            st_mtim = inner(new Timespec(getRuntime()));
            st_ctim = inner(new Timespec(getRuntime()));
            __unused4 = IS_64_BIT ? new Signed64() : null;
            __unused5 = IS_64_BIT ? new Signed64() : null;
            __unused6 = new Signed64();

            //graveyard
            st_birthtime = null;
            st_flags = null;
            st_gen = null;
            __pad1 = null;
            __pad2 = null;
        }
    }

    public final dev_t st_dev;      /* Device.  */
    private final Unsigned16 pad1;
    private final Unsigned64 __pad1;
    private final Unsigned32 __pad2;
    public final NumberField st_ino;         /* File serial number.	*/
    public final NumberField st_nlink;     /* Link count.  */
    public final NumberField st_mode;       /* File mode.  */
    public final uid_t st_uid;      /* User ID of the file's owner.	*/
    public final gid_t st_gid;      /* Group ID of the file's group.*/
    public final dev_t st_rdev;     /* Device number, if device.  */
    private final Unsigned16 pad2;
    public final NumberField st_size;        /* Size of file, in bytes.  */
    public final blksize_t st_blksize;  /* Optimal block size for I/O.  */
    public final NumberField st_blocks;   /* Number 512-byte blocks allocated. */
    public final Timespec st_atim;     /* Time of last access.  */
    public final Timespec st_mtim;     /* Time of last modification.  */
    public final Timespec st_ctim;     /* Time of last status change.  */
    public final Timespec st_birthtime;/* Time of file creation(birth) */

    public final Signed64 __unused4;
    public final Signed64 __unused5;
    public final Signed64 __unused6;

    /** MacOS specific */
    public final u_int32_t st_flags;
    public final u_int32_t st_gen;


    public static FileStat of(jnr.ffi.Pointer memory) {
        FileStat stat = new FileStat(Runtime.getSystemRuntime());
        stat.useMemory(memory);
        return stat;
    }
}
