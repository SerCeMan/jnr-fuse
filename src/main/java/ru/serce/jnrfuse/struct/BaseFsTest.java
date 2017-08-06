package ru.serce.jnrfuse.struct;

import jnr.posix.util.Platform;
import ru.serce.jnrfuse.FuseFS;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class BaseFsTest {
    protected void blockingMount(FuseFS stub, Path tmpDir) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        new Thread(() -> {
            stub.mount(tmpDir, false, true);
            latch.countDown();
        }).start();
        latch.await(1, TimeUnit.MINUTES);
    }

    protected Path tempPath() throws IOException {
        Path tmpDir;
        if(Platform.IS_WINDOWS) {
            tmpDir = Paths.get("X:\\");
        } else {
            tmpDir = Files.createTempDirectory("memfuse");
        }
        return tmpDir;
    }
}
