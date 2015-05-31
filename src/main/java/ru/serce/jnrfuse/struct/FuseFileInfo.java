package ru.serce.jnrfuse.struct;

import jnr.ffi.NativeType;
import jnr.ffi.Struct;

/**
 * Information about open files
 *
 * @author Sergey Tselovalnikov
 * @since 31.05.15
 */
public class FuseFileInfo extends Struct {
    protected FuseFileInfo(jnr.ffi.Runtime runtime) {
        super(runtime);
    }

    /**
     * Open flags.	 Available in open() and release()
     */
    public final Signed32 flags = new Signed32();
    /**
     * Old file handle, don't use
     */
    public final UnsignedLong fh_old = new UnsignedLong();
    /**
     * Can be filled in by open, to use direct I/O on this file.
     */
    public final Padding direct_io = new Padding(NativeType.UCHAR, 1);
    /**
     * Can be filled in by open, to indicate, that cached file data
     * need not be invalidated.
     */
    public final Padding keep_cache = new Padding(NativeType.UCHAR, 1);
    /**
     * Indicates a flush operation.  Set in flush operation, also
     * maybe set in highlevel lock operation and lowlevel release
     * operation.
     */
    public final Padding flush = new Padding(NativeType.UCHAR, 1);

    /**
     * Can be filled in by open, to indicate that the file is not
     * seekable.  Introduced in version 2.8
     */
    public final Padding nonseekable = new Padding(NativeType.UCHAR, 1);
    /**
     * Indicates that flock locks for this file should be
     * released.  If set, lock_owner shall contain a valid value.
     * May only be set in ->release().  Introduced in version
     * 2.9
     */
    public final Padding flock_release = new Padding(NativeType.UCHAR, 1);
    /**
     * Padding.  Do not use
     */
    public final Padding padding = new Padding(NativeType.UCHAR, 3); // 27
    /**
     * File handle.  May be filled in by filesystem in open().
     * Available in all other file operations
     */
    public final u_int64_t fh = new u_int64_t();
    /**
     * Lock owner id.  Available in locking operations and flush
     */
    public final u_int64_t lock_owner = new u_int64_t();

    public static FuseFileInfo of(jnr.ffi.Pointer pointer) {
        FuseFileInfo fi = new FuseFileInfo(jnr.ffi.Runtime.getSystemRuntime());
        fi.useMemory(pointer);
        return fi;
    }
}
