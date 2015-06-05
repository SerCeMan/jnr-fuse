package ru.serce.jnrfuse.flags;

/**
 * Values for the second argument to access. These may be OR'd together.
 * <p>
 * TODO: update JNR_CONSTANTS TO 0.8.8, and replace this to src/main/java/jnr/constants/platform/Access.java
 *
 * @author Sergey Tselovalnikov
 * @since 05.06.15
 */
public final class AccessConstants {
    public static final int R_OK = 4; /* Test for read permission.  */
    public static final int W_OK = 2; /* Test for write permission.  */
    public static final int X_OK = 1; /* Test for execute permission.  */
    public static final int F_OK = 0; /* Test for existence.  */

    private AccessConstants() {}
}
