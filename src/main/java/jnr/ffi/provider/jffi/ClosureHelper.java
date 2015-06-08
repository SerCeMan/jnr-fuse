package jnr.ffi.provider.jffi;

import jnr.ffi.Pointer;
import jnr.ffi.Runtime;
import jnr.ffi.mapper.CompositeTypeMapper;
import jnr.ffi.mapper.DefaultSignatureType;
import jnr.ffi.mapper.FromNativeContext;
import jnr.ffi.mapper.FromNativeConverter;
import jnr.ffi.provider.ClosureManager;
import ru.serce.jnrfuse.FuseException;

import java.lang.reflect.Field;
import java.util.Collections;
import java.util.concurrent.ConcurrentHashMap;

public class ClosureHelper {

    private final AsmClassLoader classLoader;
    private final CompositeTypeMapper ctm;
    private final SimpleNativeContext ctx;

    private final ConcurrentHashMap<Class<?>, FromNativeConverter<?, Pointer>> cache = new ConcurrentHashMap<>();

    public static ClosureHelper getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @SuppressWarnings("unchecked")
    public <T> FromNativeConverter<T, Pointer> getNativeConveter(Class<T> closureClass) {
        FromNativeConverter<T, Pointer> result = (FromNativeConverter<T, Pointer>) cache.get(closureClass);
        if (result != null) {
            return result;
        }
        result = (FromNativeConverter<T, Pointer>) ClosureFromNativeConverter.
                getInstance(Runtime.getSystemRuntime(), DefaultSignatureType.create(closureClass, (FromNativeContext) ctx), classLoader, ctm);
        cache.putIfAbsent(closureClass, result);
        return result;
    }

    public FromNativeContext getFromNativeContext() {
        return ctx;
    }


    private static class SingletonHolder {
        private static final ClosureHelper INSTANCE = new ClosureHelper();
    }

    private ClosureHelper() {
        try {
            ClosureManager closureManager = jnr.ffi.Runtime.getSystemRuntime().getClosureManager();
            Field classLoaderField = NativeClosureManager.class.getDeclaredField("classLoader");
            classLoaderField.setAccessible(true);

            classLoader = (AsmClassLoader) classLoaderField.get(closureManager);

            Field typeMapperField = NativeClosureManager.class.getDeclaredField("typeMapper");
            typeMapperField.setAccessible(true);
            ctm = (CompositeTypeMapper) typeMapperField.get(closureManager);
            ctx = new SimpleNativeContext(Runtime.getSystemRuntime(), Collections.emptyList());
        } catch (Exception e) {
            throw new FuseException("Unable to create helper", e);
        }
    }
}
