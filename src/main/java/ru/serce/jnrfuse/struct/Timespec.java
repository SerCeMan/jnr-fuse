package ru.serce.jnrfuse.struct;

import jnr.ffi.BaseStruct;

/**
 * POSIX.1b structure for a time value.
 *
 * @author Sergey Tselovalnikov
 * @since 31.05.15
 */
public class Timespec extends BaseStruct {
    public final __time_t tv_sec = new __time_t();          /* seconds */
    public final SignedLong tv_nsec = new SignedLong();     /* nanoseconds */

    protected Timespec(jnr.ffi.Runtime runtime) {
        super(runtime);
    }

    public static Timespec of(jnr.ffi.Pointer pointer) {
        Timespec timespec = new Timespec(jnr.ffi.Runtime.getSystemRuntime());
        timespec.useMemory(pointer);
        return timespec;
    }
}
