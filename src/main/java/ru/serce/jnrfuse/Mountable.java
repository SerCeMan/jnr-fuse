package ru.serce.jnrfuse;

import java.nio.file.Path;

public interface Mountable {
    void mount(Path mountPoint);

    void umount();
}
