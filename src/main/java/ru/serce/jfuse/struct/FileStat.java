package ru.serce.jfuse.struct;

import jnr.ffi.*;
import jnr.ffi.Runtime;

public class FileStat extends Struct {
    public FileStat(jnr.ffi.Runtime runtime) {
        super(runtime);
    }

    public final Signed64 st_dev = new Signed64();
    public final Signed64 st_ino = new Signed64();
    public final Signed64 st_nlink = new Signed64();
    public final Signed32 st_mode = new Signed32();
    public final Signed32 st_uid = new Signed32();
    public final Signed32 st_gid = new Signed32();
    public final Signed64 st_rdev = new Signed64();
    public final Signed64 st_size = new Signed64();
    public final Signed64 st_blksize = new Signed64();
    public final Signed64 st_blocks = new Signed64();
    public final Signed64 st_atime = new Signed64();     // Time of last access (time_t)
    public final Signed64 st_atimensec = new Signed64(); // Time of last access (nanoseconds)
    public final Signed64 st_mtime = new Signed64();     // Last data modification time (time_t)
    public final Signed64 st_mtimensec = new Signed64(); // Last data modification time (nanoseconds)
    public final Signed64 st_ctime = new Signed64();     // Time of last status change (time_t)
    public final Signed64 st_ctimensec = new Signed64(); // Time of last status change (nanoseconds)
    public final Signed64 __unused4 = new Signed64();
    public final Signed64 __unused5 = new Signed64();
    public final Signed64 __unused6 = new Signed64();

    public static FileStat of(jnr.ffi.Pointer memory) {
        FileStat stat = new FileStat(Runtime.getSystemRuntime());
        stat.useMemory(memory);
        return stat;
    }
}
