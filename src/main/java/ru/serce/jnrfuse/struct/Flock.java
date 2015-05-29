package ru.serce.jnrfuse.struct;

import jnr.ffi.*;

/**
 * Created by serce on 27.05.15.
 */
public class Flock extends Struct {
    protected Flock(jnr.ffi.Runtime runtime) {
        super(runtime);
    }

    public final Signed16 l_type = new Signed16();
    public final Signed16 l_whence = new Signed16();
    public final off_t l_start = new off_t();
    public final off_t l_len = new off_t();
    public final pid_t l_pid = new pid_t();

    public static Flock of(jnr.ffi.Pointer pointer) {
        Flock flock = new Flock(jnr.ffi.Runtime.getSystemRuntime());
        flock.useMemory(pointer);
        return flock;
    }
}
