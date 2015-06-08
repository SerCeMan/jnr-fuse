package ru.serce.jnrfuse.flags;

import jnr.ffi.util.EnumMapper;

/**
 * Buffer flags
 *
 * @author Sergey Tselovalnikov
 * @since 02.06.15
 */
public enum FuseBufFlags implements EnumMapper.IntegerEnum {
    /**
     * Buffer contains a file descriptor
     * <p>
     * If this flag is set, the .fd field is valid, otherwise the
     * .mem fields is valid.
     */
    FUSE_BUF_IS_FD(1 << 1),

    /**
     * Seek on the file descriptor
     * <p>
     * If this flag is set then the .pos field is valid and is
     * used to seek to the given offset before performing
     * operation on file descriptor.
     */
    FUSE_BUF_FD_SEEK(1 << 2),

    /**
     * Retry operation on file descriptor
     * <p>
     * If this flag is set then retry operation on file descriptor
     * until .size bytes have been copied or an error or EOF is
     * detected.
     */
    FUSE_BUF_FD_RETRY(1 << 3),

    /**
     * JNR does not work without null value enum
     */
    NULL_VALUE(0);

    private final int value;

    FuseBufFlags(int value) {
        this.value = value;
    }

    /**
     * Special JNR method, see jnr.ffi.util.EnumMapper#getNumberValueMethod(java.lang.Class, java.lang.Class)
     */
    @Override
    public int intValue() {
        return value;
    }
}
