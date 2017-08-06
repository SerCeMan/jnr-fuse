package ru.serce.jnrfuse.struct;

import jnr.ffi.NativeType;
import jnr.ffi.Struct;
import jnr.posix.util.Platform;

/**
 * Information about open files
 *
 * @author Sergey Tselovalnikov
 * @since 31.05.15
 */
public class FuseFileInfo extends Struct {
    // TODO: padding seems to be broken, it must be bitfield-ed instead of char by char
    protected FuseFileInfo(jnr.ffi.Runtime runtime) {
        super(runtime);
        if(!Platform.IS_WINDOWS) {
            flags = new Signed32();
            fh_old = new UnsignedLong();
            direct_io = new Padding(NativeType.UCHAR, 1);
            keep_cache = new Padding(NativeType.UCHAR, 1);
            flush = new Padding(NativeType.UCHAR, 1);
            nonseekable = new Padding(NativeType.UCHAR, 1);
            flock_release = new Padding(NativeType.UCHAR, 1);
            padding = new Padding(NativeType.UCHAR, 3);
            fh = new u_int64_t();
            lock_owner = new u_int64_t();
        } else {
            flags = new Signed32();
            fh_old = new Unsigned32();
            new Signed32(); // writepage
            direct_io = new Padding(NativeType.UCHAR, 1);
            keep_cache = new Padding(NativeType.UCHAR, 1);
            flush = new Padding(NativeType.UCHAR, 1);
            nonseekable = new Padding(NativeType.UCHAR, 1);
            flock_release = new Padding(NativeType.UCHAR, 0);
            padding = new Padding(NativeType.UCHAR, 0);
            fh = new u_int64_t();
            lock_owner = new u_int64_t();
        }
    }

    /**
     * Open flags.	 Available in open() and release()
     *
     * @see jnr.constants.platform.OpenFlags
     */
    public final Signed32 flags;
    /**
     * Old file handle, don't use
     */
    public final NumberField fh_old;
    /**
     * Can be filled in by open, to use direct I/O on this file.
     */
    public final Padding direct_io;
    /**
     * Can be filled in by open, to indicate, that cached file data
     * need not be invalidated.
     */
    public final Padding keep_cache;
    /**
     * Indicates a flush operation.  Set in flush operation, also
     * maybe set in highlevel lock operation and lowlevel release
     * operation.
     */
    public final Padding flush;

    /**
     * Can be filled in by open, to indicate that the file is not
     * seekable.  Introduced in version 2.8
     */
    public final Padding nonseekable;
    /**
     * Indicates that flock locks for this file should be
     * released.  If set, lock_owner shall contain a valid value.
     * May only be set in ->release().  Introduced in version
     * 2.9
     */
    public final Padding flock_release;
    /**
     * Padding.  Do not use
     */
    public final Padding padding; // 27
    /**
     * File handle.  May be filled in by filesystem in open().
     * Available in all other file operations
     */
    public final u_int64_t fh;
    /**
     * Lock owner id.  Available in locking operations and flush
     */
    public final u_int64_t lock_owner;

    public static FuseFileInfo of(jnr.ffi.Pointer pointer) {
        FuseFileInfo fi = new FuseFileInfo(jnr.ffi.Runtime.getSystemRuntime());
        fi.useMemory(pointer);
        return fi;
    }
}
