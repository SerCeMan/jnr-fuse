package ru.serce.jnrfuse.utils;

public final class SecurityUtils {
    public static boolean canHandleShutdownHooks() {
        SecurityManager security = System.getSecurityManager();
        if (security == null) {
            return true;
        }
        try {
            security.checkPermission(new RuntimePermission("shutdownHooks"));
            return true;
        } catch (final SecurityException e) {
            return false;
        }
    }

    private SecurityUtils() {
    }
}
