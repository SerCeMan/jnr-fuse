package ru.serce.jnrfuse.utils;

import ru.serce.jnrfuse.FuseException;

import java.io.IOException;
import java.nio.file.Path;


public class MountUtils {
    /**
     * Perform/force a umount at the provided Path
     */
    public static void umount(Path mountPoint) {
        try {
            String mountPath = mountPoint.toAbsolutePath().toString();
            try {
                new ProcessBuilder("fusermount", "-u", "-z", mountPath).start();
            } catch (IOException e) {
                new ProcessBuilder("umount", mountPath).start();
            }
        } catch (IOException e) {
            throw new FuseException("Unable to umount FS", e);
        }
    }
}
