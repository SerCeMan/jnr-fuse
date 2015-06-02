package ru.serce.jnrfuse.struct;


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
    protected FuseBufvec(jnr.ffi.Runtime runtime) {
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
    public size_t off = new size_t();

    /**
     * Array of buffers
     */
    // TODO inline and count for right size
    public FuseBuf buf = FuseBuf.of(new Pointer().getMemory());

    public static FuseBufvec of(jnr.ffi.Pointer pointer) {
        FuseBufvec buf = new FuseBufvec(jnr.ffi.Runtime.getSystemRuntime());
        buf.useMemory(pointer);
        return buf;
    }

}
