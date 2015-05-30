package ru.serce.jnrfuse.struct;

import jnr.ffi.Struct;

public class Timespec extends BaseStruct {
    protected Timespec(jnr.ffi.Runtime runtime) {
        super(runtime);
    }

    public final __time_t tv_sec = new __time_t();
    public final SignedLong tv_nsec = new SignedLong();

    public static Timespec of(jnr.ffi.Pointer pointer) {
        Timespec timespec = new Timespec(jnr.ffi.Runtime.getSystemRuntime());
        timespec.useMemory(pointer);
        return timespec;
    }
}
