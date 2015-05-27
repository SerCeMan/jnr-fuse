package ru.serce.jfuse;

import java.nio.file.Path;

public interface Mountable {
    void mount(Path mountPoint);

    void umount();
}
