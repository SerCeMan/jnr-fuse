package ru.serce.jnrfuse.struct;

import jnr.ffi.*;
import jnr.ffi.Runtime;

public class Statvfs extends BaseStruct {
    public Statvfs(jnr.ffi.Runtime runtime) {
        super(runtime);
    }

    public final UnsignedLong f_bsize = new UnsignedLong();
    public final UnsignedLong f_frsize = new UnsignedLong();
    public final fsblkcnt64_t f_blocks = new fsblkcnt64_t();
    public final fsblkcnt64_t f_bfree = new fsblkcnt64_t();
    public final fsblkcnt64_t f_bavail = new fsblkcnt64_t();
    public final fsfilcnt64_t f_files = new fsfilcnt64_t();
    public final fsfilcnt64_t f_ffree = new fsfilcnt64_t();
    public final fsfilcnt64_t f_favail = new fsfilcnt64_t();
    public final UnsignedLong f_fsid = new UnsignedLong();
    public final UnsignedLong f_flag = new UnsignedLong();
    public final UnsignedLong f_namemax = new UnsignedLong();
    public final Signed32[] __f_spare = array(new Signed32[6]);


    public static Statvfs of(jnr.ffi.Pointer pointer) {
        Statvfs statvfs = new Statvfs(jnr.ffi.Runtime.getSystemRuntime());
        statvfs.useMemory(pointer);
        return statvfs;
    }
}
