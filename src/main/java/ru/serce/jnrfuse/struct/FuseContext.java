package ru.serce.jnrfuse.struct;

import jnr.ffi.BaseStruct;
import jnr.ffi.Runtime;

/**
 * Extra context that may be needed by some filesystems
 *
 * The uid, gid and pid fields are not filled in case of a writepage
 * operation.
 */
public class FuseContext extends BaseStruct {
    /** Pointer to the fuse object */
    public final Pointer fuse = new Pointer();
    /** User ID of the calling process */
    public final uid_t uid = new uid_t();
    /** Group ID of the calling process */
    public final gid_t gid = new gid_t();
    /** Thread ID of the calling process */
    public final pid_t pid = new pid_t();
    /** Private filesystem data */
    public final Pointer private_data = new Pointer();
    /** Umask of the calling process (introduced in version 2.8) */
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