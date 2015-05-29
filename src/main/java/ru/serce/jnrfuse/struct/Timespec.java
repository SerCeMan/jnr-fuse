package ru.serce.jnrfuse.struct;

import jnr.ffi.Struct;

public class Timespec extends Struct {
    protected Timespec(jnr.ffi.Runtime runtime) {
        super(runtime);
    }

    public final time_t tv_sec = new time_t();
    public final Signed32 tv_nsec = new Signed32();

    public static Timespec of(jnr.ffi.Pointer pointer) {
        Timespec timespec = new Timespec(jnr.ffi.Runtime.getSystemRuntime());
        timespec.useMemory(pointer);
        return timespec;
    }
}
