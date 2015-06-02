package ru.serce.jnrfuse.struct;


import jnr.ffi.*;
import jnr.ffi.Runtime;

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

}
