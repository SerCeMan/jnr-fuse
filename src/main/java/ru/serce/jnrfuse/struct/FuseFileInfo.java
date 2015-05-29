package ru.serce.jnrfuse.struct;

import jnr.ffi.*;

public class FuseFileInfo extends Struct {
    protected FuseFileInfo(jnr.ffi.Runtime runtime) {
        super(runtime);
    }

    public final Signed32 flags = new Signed32();
    public final Unsigned64 fh_old = new Unsigned64();
    public final Padding direct_io = new Padding(NativeType.UINT, 1);
    public final Padding keep_cache = new Padding(NativeType.UINT, 1);
    public final Padding flush = new Padding(NativeType.UINT, 1);
    public final Padding nonseekable = new Padding(NativeType.UINT, 1);
    public final Padding flock_release = new Padding(NativeType.UINT, 1);
    public final Padding padding = new Padding(NativeType.UINT, 27);
    public final u_int64_t fh = new u_int64_t();
    public final u_int64_t lock_owner = new u_int64_t();

    public static FuseFileInfo of(jnr.ffi.Pointer pointer) {
        FuseFileInfo fi = new FuseFileInfo(jnr.ffi.Runtime.getSystemRuntime());
        fi.useMemory(pointer);
        return fi;
    }
}
