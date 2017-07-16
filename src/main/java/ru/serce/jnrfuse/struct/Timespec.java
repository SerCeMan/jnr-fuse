package ru.serce.jnrfuse.struct;

import jnr.ffi.BaseStruct;
import jnr.posix.util.Platform;

/**
 * POSIX.1b structure for a time value.
 *
 * @author Sergey Tselovalnikov
 * @since 31.05.15
 */
public class Timespec extends BaseStruct {
    protected Timespec(jnr.ffi.Runtime runtime) {
        super(runtime);
        if(!Platform.IS_WINDOWS) {
            tv_nsec = new SignedLong();
        } else {
            tv_nsec = new Signed64();
        }
    }

    public final time_t tv_sec = new time_t();     /* seconds */
    public final NumberField tv_nsec;              /* nanoseconds */

    public static Timespec of(jnr.ffi.Pointer pointer) {
        Timespec timespec = new Timespec(jnr.ffi.Runtime.getSystemRuntime());
        timespec.useMemory(pointer);
        return timespec;
    }
}
