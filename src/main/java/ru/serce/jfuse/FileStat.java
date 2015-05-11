package ru.serce.jfuse;

import jnr.ffi.Struct;

/**
 * Created by serce on 09.05.15.
 */
public class FileStat extends Struct {
    public FileStat(jnr.ffi.Runtime runtime) {
        super(runtime);
    }

    public final Struct.Signed64 st_dev = new Struct.Signed64();
    public final Struct.Signed64 st_ino = new Struct.Signed64();
    public final Struct.Signed64 st_nlink = new Struct.Signed64();
    public final Struct.Signed32 st_mode = new Struct.Signed32();
    public final Struct.Signed32 st_uid = new Struct.Signed32();
    public final Struct.Signed32 st_gid = new Struct.Signed32();
    public final Struct.Signed64 st_rdev = new Struct.Signed64();
    public final Struct.Signed64 st_size = new Struct.Signed64();
    public final Struct.Signed64 st_blksize = new Struct.Signed64();
    public final Struct.Signed64 st_blocks = new Struct.Signed64();
    public final Struct.Signed64 st_atime = new Struct.Signed64();     // Time of last access (time_t)
    public final Struct.Signed64 st_atimensec = new Struct.Signed64(); // Time of last access (nanoseconds)
    public final Struct.Signed64 st_mtime = new Struct.Signed64();     // Last data modification time (time_t)
    public final Struct.Signed64 st_mtimensec = new Struct.Signed64(); // Last data modification time (nanoseconds)
    public final Struct.Signed64 st_ctime = new Struct.Signed64();     // Time of last status change (time_t)
    public final Struct.Signed64 st_ctimensec = new Struct.Signed64(); // Time of last status change (nanoseconds)
    public final Struct.Signed64 __unused4 = new Struct.Signed64();
    public final Struct.Signed64 __unused5 = new Struct.Signed64();
    public final Struct.Signed64 __unused6 = new Struct.Signed64();
}
