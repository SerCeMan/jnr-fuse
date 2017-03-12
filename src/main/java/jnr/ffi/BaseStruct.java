package jnr.ffi;

public abstract class BaseStruct extends Struct {
    protected BaseStruct(jnr.ffi.Runtime runtime) {
        super(runtime);
    }

    public class Func<T> extends AbstractMember {
        private final Class<? extends T> closureClass;
        @SuppressWarnings("unused")
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

    public class fsblkcnt64_t extends Signed64 {
    }

    public class fsfilcnt64_t extends Signed64 {
    }

    public class __time_t extends SignedLong {
    }

    public class __off64_t extends Signed64 {
    }



}
