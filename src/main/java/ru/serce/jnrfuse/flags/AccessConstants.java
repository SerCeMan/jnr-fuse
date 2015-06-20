package ru.serce.jnrfuse.flags;

import jnr.constants.platform.Access;

/**
 * Values for the second argument to access. These may be OR'd together.
 * <p>
 *
 * @author Sergey Tselovalnikov
 * @since 05.06.15
 */
public final class AccessConstants {
    public static final int R_OK = Access.R_OK.intValue(); /* Test for read permission.  */
    public static final int W_OK = Access.W_OK.intValue(); /* Test for write permission.  */
    public static final int X_OK = Access.X_OK.intValue(); /* Test for execute permission.  */
    public static final int F_OK = Access.F_OK.intValue(); /* Test for existence.  */

    private AccessConstants() {}
}
