package ru.serce.jnrfuse.struct;


import jnr.ffi.BaseStruct;

/**
 * Data buffer vector
 * <p>
 * An array of data buffers, each containing a memory pointer or a
 * file descriptor.
 * <p>
 * Allocate dynamically to add more than one buffer.
 *
 * @author Sergey Tselovalnikov
 * @since 02.06.15
 */
public class FuseBufvec extends BaseStruct {
    public FuseBufvec(jnr.ffi.Runtime runtime) {
        super(runtime);
    }

    /**
     * Number of buffers in the array
     */
    public final size_t count = new size_t();

    /**
     * Index of current buffer within the array
     */
    public final size_t idx = new size_t();

    /**
     * Current offset within the current buffer
     */
    public final size_t off = new size_t();

    /**
     * Array of buffers
     */
    public final FuseBuf buf = inner(new FuseBuf(getRuntime()));

    public static FuseBufvec of(jnr.ffi.Pointer pointer) {
        FuseBufvec buf = new FuseBufvec(jnr.ffi.Runtime.getSystemRuntime());
        buf.useMemory(pointer);
        return buf;
    }

    /**
     * Similar to FUSE_BUFVEC_INIT macros
     */
    public static void init(FuseBufvec buf, long size) {
        buf.count.set(1);
        buf.idx.set(0);
        buf.off.set(0);
        buf.buf.size.set(size);
        buf.buf.flags.set(0);
        buf.buf.mem.set(0);
        buf.buf.fd.set(-1);
        buf.buf.pos.set(0);
    }
}
