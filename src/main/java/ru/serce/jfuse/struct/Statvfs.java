package ru.serce.jfuse.struct;

import jnr.ffi.*;

public class Statvfs extends Struct {
    protected Statvfs(jnr.ffi.Runtime runtime) {
        super(runtime);
    }

    // TODO check size
    public final Unsigned32 f_bsize = new Unsigned32();
    public final Unsigned32 f_frsize = new Unsigned32();
    public final fsblkcnt_t f_blocks = new fsblkcnt_t();
    public final fsblkcnt_t f_bfree = new fsblkcnt_t();
    public final fsblkcnt_t f_bavail = new fsblkcnt_t();
    public final fsfilcnt_t f_files = new fsfilcnt_t();
    public final fsfilcnt_t f_ffree = new fsfilcnt_t();
    public final fsfilcnt_t f_favail = new fsfilcnt_t();


    public static Statvfs of(jnr.ffi.Pointer pointer) {
        Statvfs statvfs = new Statvfs(jnr.ffi.Runtime.getSystemRuntime());
        statvfs.useMemory(pointer);
        return statvfs;
    }
}
