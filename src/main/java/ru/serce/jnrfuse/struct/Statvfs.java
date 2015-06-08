package ru.serce.jnrfuse.struct;

import jnr.ffi.BaseStruct;
import jnr.posix.util.Platform;

/**
 * Information about a mounted file system
 *
 * @author Sergey Tselovalnikov
 * @since 31.05.15
 */
public class Statvfs extends BaseStruct {
    /* Definitions for the flag in `f_flag'.*/
    public static final int ST_RDONLY = 1;		/* Mount read-only.  */
    public static final int ST_NOSUID = 2;		/* Ignore suid and sgid bits.  */
    public static final int ST_NODEV = 4;		/* Disallow access to device special files.  */
    public static final int ST_NOEXEC = 8;		/* Disallow program execution.  */
    public static final int ST_SYNCHRONOUS = 16;/* Writes are synced at once.  */
    public static final int ST_MANDLOCK = 64;	/* Allow mandatory locks on an FS.  */
    public static final int ST_WRITE = 128;		/* Write on file/directory/symlink.  */
    public static final int ST_APPEND = 256;	/* Append-only file.  */
    public static final int ST_IMMUTABLE = 512;	/* Immutable file.  */
    public static final int ST_NOATIME = 1024;	/* Do not update access times.  */
    public static final int ST_NODIRATIME = 2048;/* Do not update directory access times.  */
    public static final int ST_RELATIME = 4096;	/* Update atime relative to mtime/ctime.  */


    public Statvfs(jnr.ffi.Runtime runtime) {
        super(runtime);
    }

    public final UnsignedLong f_bsize = new UnsignedLong();  /* file system block size */
    public final UnsignedLong f_frsize = new UnsignedLong(); /* fragment size */
    public final fsblkcnt64_t f_blocks = new fsblkcnt64_t(); /* size of fs in f_frsize units */
    public final fsblkcnt64_t f_bfree = new fsblkcnt64_t();  /* # free blocks */
    public final fsblkcnt64_t f_bavail = new fsblkcnt64_t(); /* # free blocks for non-root */
    public final fsfilcnt64_t f_files = new fsfilcnt64_t();  /* # inodes */
    public final fsfilcnt64_t f_ffree = new fsfilcnt64_t();  /* # free inodes */
    public final fsfilcnt64_t f_favail = new fsfilcnt64_t(); /* # free inodes for non-root */
    public final UnsignedLong f_fsid = new UnsignedLong();   /* file system ID */
    public final Signed32 f_unused;

    {
        f_unused = Platform.IS_32_BIT ? new Signed32() : null;
    }

    public final UnsignedLong f_flag = new UnsignedLong();   /* mount flags */
    public final UnsignedLong f_namemax = new UnsignedLong();/* maximum filename length */
    public final Signed32[] __f_spare = array(new Signed32[6]);


    public static Statvfs of(jnr.ffi.Pointer pointer) {
        Statvfs statvfs = new Statvfs(jnr.ffi.Runtime.getSystemRuntime());
        statvfs.useMemory(pointer);
        return statvfs;
    }
}
