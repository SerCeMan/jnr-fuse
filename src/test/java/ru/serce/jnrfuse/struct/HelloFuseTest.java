package ru.serce.jnrfuse.struct;

import org.junit.Test;
import ru.serce.jnrfuse.examples.HelloFuse;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class HelloFuseTest extends BaseFsTest {
    @Test
    public void testReadHello() throws Exception {
        if (!canRunTest()) {
            System.out.println("can't run the test on this platform");
            return;
        }
        HelloFuse stub = new HelloFuse();

        Path tmpDir = Files.createTempDirectory("hellofuse");
        blockingMount(stub, tmpDir);
        try {
            File helloFile = new File(tmpDir.toAbsolutePath() + HelloFuse.HELLO_PATH);

            assertTrue(helloFile.exists());
            assertEquals(HelloFuse.HELLO_STR, Files.lines(helloFile.toPath()).collect(Collectors.joining()));
        } finally {
            stub.umount();
        }
    }
}
