package ru.serce.jfuse.struct;

import jnr.ffi.*;

public class FuseStruct extends Struct {
    protected FuseStruct(jnr.ffi.Runtime runtime) {
        super(runtime);
    }

    public class Func<T> extends AbstractMember {
        private final Class<? extends T> closureClass;
        private T instance;

        public Func(Class<? extends T> closureClass) {
            super(NativeType.ADDRESS);
            this.closureClass = closureClass;
        }

        public final void set(T value) {
            getMemory().putPointer(offset(), getRuntime().getClosureManager().getClosurePointer(closureClass, instance = value));
        }
    }

    protected final <T> Func<T> func(Class<T> closureClass) {
        return new Func<>(closureClass);
    }
}
