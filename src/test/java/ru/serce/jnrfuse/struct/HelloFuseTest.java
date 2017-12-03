package ru.serce.jnrfuse.struct;

import jnr.posix.util.Platform;
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
        if (Platform.IS_WINDOWS) {
            // There is a winfsp speciality due to which the FS can't be mounted, unmounted, and then mounted again
            // https://github.com/billziss-gh/cgofuse/issues/21#issuecomment-345307900
            // Therefore only one mounting test test is currently enabled. Given that HelloFS is not as valuable as
            // MemoryFS it is disabled.
            return;
        }
        HelloFuse stub = new HelloFuse();

        Path tmpDir = tempPath();
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
