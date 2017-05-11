package ru.serce.jnrfuse.struct;

import jnr.ffi.BaseStruct;
import jnr.ffi.Runtime;

public class FuseContext extends BaseStruct {
    public final Pointer fuse = new Pointer();
    public final uid_t uid = new uid_t();
    public final gid_t gid = new gid_t();
    public final pid_t pid = new pid_t();
    public final Pointer private_data = new Pointer();
    public final mode_t umask = new mode_t();

    public FuseContext(Runtime runtime) {
        super(runtime);
    }

    public static FuseContext of(jnr.ffi.Pointer pointer) {
        FuseContext fc = new FuseContext(jnr.ffi.Runtime.getSystemRuntime());
        fc.useMemory(pointer);
        return fc;
    }
}