package ru.serce.jnrfuse.struct;

import jnr.ffi.BaseStruct;

/**
 * @see "fuse_lowlevel.c"
 *
 * <pre>
 * struct fuse_pollhandle {
 *   uint64_t kh;
 *   struct fuse_chan *ch;
 *   struct fuse_ll *f;
 * };
 * </pre>
 *
 * @author Sergey Tselovalnikov
 * @since 02.06.15
 */
public class FusePollhandle extends BaseStruct {
    public final Unsigned64 kh = new Unsigned64();
    // TODO struct fuse_chan *ch;
    public final Pointer ch = new Pointer();
    // TODO struct fuse_ll *f;
    public final Pointer f = new Pointer();

    protected FusePollhandle(jnr.ffi.Runtime runtime) {
        super(runtime);
    }

    public static FusePollhandle of(jnr.ffi.Pointer pointer) {
        FusePollhandle ph = new FusePollhandle(jnr.ffi.Runtime.getSystemRuntime());
        ph.useMemory(pointer);
        return ph;
    }
}
