package ru.serce.jnrfuse.struct;

import jnr.ffi.Struct;
import jnr.posix.util.Platform;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Test for right struct size
 *
 * @author Sergey Tselovalnikov
 * @since 31.05.15
 */
public class StructSizeTest {

    @Test
    public void testStatvfs() {
        if (Platform.IS_64_BIT) {
            assertEquals(112, Struct.size(new Statvfs(jnr.ffi.Runtime.getSystemRuntime())));
        }
        if (Platform.IS_32_BIT) {
            assertEquals(96, Struct.size(new Statvfs(jnr.ffi.Runtime.getSystemRuntime())));
        }
    }

    @Test
    public void testFileStat() {
        if (Platform.IS_64_BIT) {
            assertEquals(144, Struct.size(new FileStat(jnr.ffi.Runtime.getSystemRuntime())));
        }
        if (Platform.IS_32_BIT) {
            assertEquals(96, Struct.size(new FileStat(jnr.ffi.Runtime.getSystemRuntime())));
        }
    }

    @Test
    public void testFuseFileInfo() {
        if (Platform.IS_64_BIT) {
            assertEquals(40, Struct.size(new FuseFileInfo(jnr.ffi.Runtime.getSystemRuntime())));
        }
        if (Platform.IS_32_BIT) {
            assertEquals(32, Struct.size(new FuseFileInfo(jnr.ffi.Runtime.getSystemRuntime())));
        }
    }

    @Test
    public void testFuseOperations() {
        if (Platform.IS_64_BIT) {
            assertEquals(360, Struct.size(new FuseOperations(jnr.ffi.Runtime.getSystemRuntime())));
        }
        if (Platform.IS_32_BIT) {
            assertEquals(180, Struct.size(new FuseOperations(jnr.ffi.Runtime.getSystemRuntime())));
        }
    }

    @Test
    public void testTimeSpec() {
        if (Platform.IS_64_BIT) {
            assertEquals(16, Struct.size(new Timespec(jnr.ffi.Runtime.getSystemRuntime())));
        }
        if (Platform.IS_32_BIT) {
            assertEquals(8, Struct.size(new Timespec(jnr.ffi.Runtime.getSystemRuntime())));
        }
    }

    @Test
    public void testFlock() {
        if (Platform.IS_64_BIT) {
            assertEquals(32, Struct.size(new Flock(jnr.ffi.Runtime.getSystemRuntime())));
        }
        if (Platform.IS_32_BIT) {
            assertEquals(24, Struct.size(new Flock(jnr.ffi.Runtime.getSystemRuntime())));
        }
    }

    @Test
    public void testFuseBuf() {
        if (Platform.IS_64_BIT) {
            assertEquals(40, Struct.size(new FuseBuf(jnr.ffi.Runtime.getSystemRuntime())));
        }
        if (Platform.IS_32_BIT) {
            assertEquals(24, Struct.size(new FuseBuf(jnr.ffi.Runtime.getSystemRuntime())));
        }
    }

    @Test
    public void testFuseBufvec() {
        if (Platform.IS_64_BIT) {
            assertEquals(64, Struct.size(new FuseBufvec(jnr.ffi.Runtime.getSystemRuntime())));
        }
        if (Platform.IS_32_BIT) {
            assertEquals(36, Struct.size(new FuseBufvec(jnr.ffi.Runtime.getSystemRuntime())));
        }
    }

    @Test
    public void testFusePollhandle() {
        if (Platform.IS_64_BIT) {
            assertEquals(24, Struct.size(new FusePollhandle(jnr.ffi.Runtime.getSystemRuntime())));
        }
        if (Platform.IS_32_BIT) {
            assertEquals(16, Struct.size(new FusePollhandle(jnr.ffi.Runtime.getSystemRuntime())));
        }
    }
}