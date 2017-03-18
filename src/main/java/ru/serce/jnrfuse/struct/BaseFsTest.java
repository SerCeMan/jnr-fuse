package ru.serce.jnrfuse.struct;

import ru.serce.jnrfuse.FuseFS;

import java.nio.file.Path;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class BaseFsTest {
    protected boolean canRunTest() {
        // Travis runs jobs in container where fuse isn't available
        return !"linux".equals(System.getenv("TRAVIS_OS_NAME"));
    }

    protected void blockingMount(FuseFS stub, Path tmpDir) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        new Thread(() -> {
            stub.mount(tmpDir, false, true);
            latch.countDown();
        }).start();
        latch.await(1, TimeUnit.MINUTES);
    }
}
