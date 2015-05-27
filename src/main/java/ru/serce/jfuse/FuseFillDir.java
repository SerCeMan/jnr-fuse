package ru.serce.jfuse;

import jnr.ffi.Pointer;
import jnr.ffi.annotations.Delegate;
import jnr.ffi.types.off_t;

import java.nio.Buffer;
import java.nio.ByteBuffer;

public interface FuseFillDir {
    /** Function to add an entry in a readdir() operation
     *
     * @param buf the buffer passed to the readdir() operation
     * @param name the file name of the directory entry
     * @param stbuf file attributes, can be NULL
     * @param off offset of the next entry or zero
     * @return 1 if buffer is full, zero otherwise
     */
    @Delegate
    int fuseFillDir(Pointer buf, ByteBuffer name, Pointer stbuf, @off_t int off);


    default int apply(Pointer buf, String name, Pointer stbuf, @off_t int off) {
        return fuseFillDir(buf, ByteBuffer.wrap(name.getBytes()), stbuf, off);
    }
}
