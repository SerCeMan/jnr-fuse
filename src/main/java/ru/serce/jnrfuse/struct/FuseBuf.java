package ru.serce.jnrfuse.struct;

/**
 * Single data buffer
 * <p>
 * Generic data buffer for I/O, extended attributes, etc...  Data may
 * be supplied as a memory pointer or as a file descriptor
 */
public class FuseBuf extends BaseStruct {
    protected FuseBuf(jnr.ffi.Runtime runtime) {
        super(runtime);
    }

    /**
     * Size of data in bytes
     */
    public final size_t size = new size_t();

    /**
     * Buffer flags
     */
    public final Enum<FuseBufFlags> flags = new Enum<>(FuseBufFlags.class);

    /**
     * Memory pointer
     * <p>
     * Used unless FUSE_BUF_IS_FD flag is set.
     */
    public final Pointer mem = new Pointer();

    /**
     * File descriptor
     * <p>
     * Used if FUSE_BUF_IS_FD flag is set.
     */
    public final Signed32 fd = new Signed32();

    /**
     * File position
     * <p>
     * Used if FUSE_BUF_FD_SEEK flag is set.
     */
    public final off_t pos = new off_t();

    public static FuseBuf of(jnr.ffi.Pointer pointer) {
        FuseBuf buf = new FuseBuf(jnr.ffi.Runtime.getSystemRuntime());
        buf.useMemory(pointer);
        return buf;
    }
}
