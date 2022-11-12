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
    protected void blockingMount(FuseFS fs, Path tmpDir) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        new Thread(() -> {
            try {
                fs.mount(tmpDir, false, true);
                latch.countDown();
            } catch (Throwable t) {
                // print the error and return the unreleased latch
                System.err.println("failed to mount the FS");
                t.printStackTrace();
            }
        }).start();
        if (!latch.await(2, TimeUnit.MINUTES)) {
            throw new RuntimeException("mount took too long");
        }
    }

    protected Path tempPath() throws IOException {
        Path tmpDir;
        if (Platform.IS_WINDOWS) {
            tmpDir = Paths.get("M:\\");
        } else {
            tmpDir = Files.createTempDirectory("memfuse");
        }
        return tmpDir;
    }
}
