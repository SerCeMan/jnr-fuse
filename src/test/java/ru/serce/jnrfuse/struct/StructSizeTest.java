package ru.serce.jnrfuse.struct;

import jnr.ffi.Runtime;
import jnr.ffi.Struct;
import jnr.posix.util.Platform;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Map;
import java.util.function.Function;

import static org.junit.Assert.assertEquals;
import static ru.serce.jnrfuse.struct.Utils.Pair.pair;
import static ru.serce.jnrfuse.struct.PlatformSize.platformSize;
import static jnr.ffi.Platform.OS.*;
import static ru.serce.jnrfuse.struct.Utils.asMap;

class PlatformSize {
    public final int x32;
    public final int x64;

    PlatformSize(int x32, int x64) {
        this.x32 = x32;
        this.x64 = x64;
    }

    public static PlatformSize platformSize(int x32, int x64) {
        return new PlatformSize(x32, x64);
    }
}


/**
 * Test for the right struct size
 */
public class StructSizeTest {

    private static Map<Class<?>, Map<jnr.ffi.Platform.OS, PlatformSize>> sizes = asMap(
            pair(Statvfs.class, asMap(
                    pair(LINUX, platformSize(96, 112)), //
                    pair(WINDOWS, platformSize(88, 88)), //
                    pair(DARWIN, platformSize(64, 64)))),
            pair(FileStat.class, asMap(
                    pair(LINUX, platformSize(96, 144)), //
                    pair(WINDOWS, platformSize(128, 128)), //
                    pair(DARWIN, platformSize(96, 144)))),
            pair(FuseFileInfo.class, asMap(
                    pair(LINUX, platformSize(32, 40)), //
                    pair(WINDOWS, platformSize(32, 32)), //
                    pair(DARWIN, platformSize(32, 40)))),
            pair(FuseOperations.class, asMap(
                    pair(LINUX, platformSize(180, 360)), //
                    pair(WINDOWS, platformSize(180, 360)), //
                    pair(DARWIN, platformSize(232, 464)))),
            pair(Timespec.class, asMap(
                    pair(LINUX, platformSize(8, 16)), //
                    pair(WINDOWS, platformSize(8, 16)), //
                    pair(DARWIN, platformSize(8, 16)))),
            pair(Flock.class, asMap(
                    pair(LINUX, platformSize(24, 32)), //
                    pair(DARWIN, platformSize(24, 24)))),
            pair(FuseBuf.class, asMap(
                    pair(LINUX, platformSize(24, 40)), //
                    pair(DARWIN, platformSize(24, 40)))),
            pair(FuseBufvec.class, asMap(
                    pair(LINUX, platformSize(36, 64)), //
                    pair(DARWIN, platformSize(36, 64)))),
            pair(FusePollhandle.class, asMap(
                    pair(LINUX, platformSize(16, 24)), //
                    pair(DARWIN, platformSize(16, 24)))),
            pair(FuseContext.class, asMap(
                    // the real x64 size is 40 because of alignment
                    pair(LINUX, platformSize(24, 36)), //
                    pair(WINDOWS, platformSize(24, 34)), //
                    pair(DARWIN, platformSize(24, 34))))
    );

    @BeforeClass
    public static void init() {
        if (!Platform.IS_32_BIT && !Platform.IS_64_BIT) {
            throw new IllegalStateException("Unknown platform " + System.getProperty("sun.arch.data.model"));
        }
        System.out.println("Running struct size test\nPlatform: " + Platform.ARCH + "\nOS: " + Platform.OS_NAME);
    }

    private static void assertPlatfomValue(Function<jnr.ffi.Runtime, Struct> structFunction) {
        Struct struct = structFunction.apply(Runtime.getSystemRuntime());
        jnr.ffi.Platform.OS os = jnr.ffi.Platform.getNativePlatform().getOS();
        PlatformSize size = sizes.get(struct.getClass()).get(os);
        assertEquals(Platform.IS_32_BIT ? size.x32 : size.x64, Struct.size(struct));
    }


    @Test
    public void testStatvfs() {
        assertPlatfomValue(Statvfs::new);
    }

    @Test
    public void testFileStat() {
        assertPlatfomValue(FileStat::new);
    }

    @Test
    public void testFuseFileInfo() {
        assertPlatfomValue(FuseFileInfo::new);
    }

    @Test
    public void testFuseOperations() {
        assertPlatfomValue(FuseOperations::new);
    }

    @Test
    public void testTimeSpec() {
        assertPlatfomValue(Timespec::new);
    }

    @Test
    public void testFlock() {
        assertPlatfomValue(Flock::new);
    }

    @Test
    public void testFuseBuf() {
        assertPlatfomValue(FuseBuf::new);
    }

    @Test
    public void testFuseBufvec() {
        assertPlatfomValue(FuseBufvec::new);
    }

    @Test
    public void testFusePollhandle() {
        assertPlatfomValue(FusePollhandle::new);
    }

    @Test
    public void testFuseContext() {
        System.out.println("st_dev " + Struct.size(new Struct(Runtime.getSystemRuntime()) {{ new dev_t(); }}));
        System.out.println("u_int64_t " + Struct.size(new Struct(Runtime.getSystemRuntime()) {{ new u_int64_t(); }}));
        System.out.println("st_mode " + Struct.size(new Struct(Runtime.getSystemRuntime()) {{ new Signed32(); }}));
        System.out.println("st_nlink " + Struct.size(new Struct(Runtime.getSystemRuntime()) {{ new nlink_t(); }}));
        System.out.println("uid_t " + Struct.size(new Struct(Runtime.getSystemRuntime()) {{ new uid_t(); }}));
        System.out.println("gid_t " + Struct.size(new Struct(Runtime.getSystemRuntime()) {{ new gid_t(); }}));
        System.out.println("st_rdev " + Struct.size(new Struct(Runtime.getSystemRuntime()) {{ new dev_t(); }}));
        System.out.println("off_t " + Struct.size(new Struct(Runtime.getSystemRuntime()) {{ new off_t(); }}));
        System.out.println("st_atim " + Struct.size(new Struct(Runtime.getSystemRuntime()) {{ inner(new Timespec(getRuntime())); }}));
        System.out.println("blksize_t " + Struct.size(new Struct(Runtime.getSystemRuntime()) {{ new blksize_t(); }}));
        System.out.println("st_blocks " + Struct.size(new Struct(Runtime.getSystemRuntime()) {{ new blkcnt_t(); }}));
        System.out.println("fuse_mode_t " + Struct.size(new Struct(Runtime.getSystemRuntime()) {{ new mode_t(); }}));
        assertPlatfomValue(FuseContext::new);

    }
}