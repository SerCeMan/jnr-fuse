package ru.serce.jnrfuse.struct;

import jnr.ffi.BaseStruct;
import jnr.ffi.Runtime;

public class FuseContext extends BaseStruct {
    public final uid_t uid = new uid_t();
    public final gid_t gid = new gid_t();
    public final pid_t pid = new pid_t();
    public final Pointer private_data = new Pointer();

    public FuseContext(Runtime runtime) {
        super(runtime);
    }

/*    public java.lang.String toString() {
        return "uid=" + uid.get() + " gid=" + gid.get() + " pid=" + pid.get();
    }*/
}