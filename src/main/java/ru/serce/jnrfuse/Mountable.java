package ru.serce.jnrfuse;

import java.nio.file.Path;

public interface Mountable {
    void mount(Path mountPoint, boolean blocking);

    void umount();

    default void mount(Path mountPoint) {
        mount(mountPoint, false);
    }
}
