package ru.serce.jnrfuse.struct;

/**
 * @see "fuse_lowlevel.c"
 *
 * @author Sergey Tselovalnikov
 * @since 02.06.15
 */
public class FusePollhandle extends BaseStruct {
    protected FusePollhandle(jnr.ffi.Runtime runtime) {
        super(runtime);
    }

    public final Unsigned64 kh = new Unsigned64();
    // TODO struct fuse_chan *ch;
    public final Pointer ch = new Pointer();
    // TODO struct fuse_ll *f;
    public final Pointer f = new Pointer();

    public static FusePollhandle of(jnr.ffi.Pointer pointer) {
        FusePollhandle ph = new FusePollhandle(jnr.ffi.Runtime.getSystemRuntime());
        ph.useMemory(pointer);
        return ph;
    }
}
