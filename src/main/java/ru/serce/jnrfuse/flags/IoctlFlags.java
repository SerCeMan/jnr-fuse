package ru.serce.jnrfuse.flags;

/**
 * @author Sergey Tselovalnikov
 * @since 05.06.15
 */
public final class IoctlFlags {

    // flags
    public static final int _IOC_NONE = 0;
    public static final int _IOC_READ = 2;
    public static final int _IOC_WRITE = 1;


    // macros
    public static final int _IOC_SIZEBITS = 14;
    public static final int _IOC_TYPEBITS = 8;
    public static final int _IOC_NRBITS = 8;
    public static final int _IOC_NRSHIFT = 0;

    public static int _IOC_TYPESHIFT() {
        return (_IOC_NRSHIFT + _IOC_NRBITS);
    }

    public static int _IOC_SIZESHIFT() {
        return (_IOC_TYPESHIFT() + _IOC_TYPEBITS);
    }

    public static int _IOC_SIZEMASK() {
        return ((1 << _IOC_SIZEBITS) - 1);
    }

    public static int _IOC_SIZE(int nr) {
        return (((nr) >> _IOC_SIZESHIFT()) & _IOC_SIZEMASK());
    }

    private IoctlFlags() {
    }
}
