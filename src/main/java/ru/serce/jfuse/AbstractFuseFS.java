package ru.serce.jfuse;

import jnr.ffi.*;
import jnr.ffi.Runtime;
import jnr.ffi.mapper.FromNativeConverter;
import jnr.ffi.provider.jffi.ClosureHelper;
import jnr.ffi.provider.jffi.FuseException;
import ru.serce.jfuse.struct.FileStat;
import ru.serce.jfuse.struct.Flock;
import ru.serce.jfuse.struct.FuseFileInfo;
import ru.serce.jfuse.struct.FuseOperations;
import ru.serce.jfuse.struct.Statvfs;
import ru.serce.jfuse.struct.Timespec;

import java.io.IOException;
import java.nio.file.Path;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by serce on 27.05.15.
 */
public abstract class AbstractFuseFS implements FuseFS {

    private final LibFuse libFuse;
    private final FuseOperations fuseOperations;
    private final AtomicBoolean mounted = new AtomicBoolean();
    private Path mountPoint;

    public AbstractFuseFS() {
        LibraryLoader<LibFuse> loader = LibraryLoader.create(LibFuse.class);
        libFuse = loader.load("libfuse.so.2");

        Runtime runtime = Runtime.getSystemRuntime();
        fuseOperations = new FuseOperations(runtime);
        init(fuseOperations);
    }

    private void init(FuseOperations fuseOperations) {
        AbstractFuseFS fuse = this;
        fuseOperations.getattr.set((path, stbuf) -> fuse.getattr(path, FileStat.of(stbuf)));
        fuseOperations.readlink.set(fuse::readlink);
        fuseOperations.mknod.set(fuse::mknod);
        fuseOperations.mkdir.set(fuse::mkdir);
        fuseOperations.unlink.set(fuse::unlink);
        fuseOperations.rmdir.set(fuse::rmdir);
        fuseOperations.symlink.set(fuse::symlink);
        fuseOperations.rename.set(fuse::rename);
        fuseOperations.chmod.set(fuse::chmod);
        fuseOperations.chown.set(fuse::chown);
        fuseOperations.truncate.set(fuse::truncate);
        fuseOperations.open.set((path, fi) -> fuse.open(path, FuseFileInfo.of(fi)));
        fuseOperations.read.set((path, buf, size, offset, fi) -> fuse.read(path, buf, size, offset, FuseFileInfo.of(fi)));
        fuseOperations.write.set((path, buf, size, offset, fi) -> fuse.write(path, buf, size, offset, FuseFileInfo.of(fi)));
        fuseOperations.statfs.set((path, stbuf) -> fuse.statfs(path, Statvfs.of(stbuf)));
        fuseOperations.flush.set((path, fi) -> fuse.flush(path, FuseFileInfo.of(fi)));
        fuseOperations.release.set((path, fi) -> fuse.release(path, FuseFileInfo.of(fi)));
        fuseOperations.fsync.set((path, isdatasync, fi) -> fuse.fsync(path, isdatasync, FuseFileInfo.of(fi)));
        fuseOperations.setxattr.set(fuse::setxattr);
        fuseOperations.getxattr.set(fuse::getxattr);
        fuseOperations.listxattr.set(fuse::listxattr);
        fuseOperations.removexattr.set(fuse::removexattr);
        fuseOperations.opendir.set((path, fi) -> fuse.opendir(path, FuseFileInfo.of(fi)));
        fuseOperations.readdir.set((path, buf, filter, offset, fi) -> {
            ClosureHelper helper = ClosureHelper.getInstance();
            FromNativeConverter<FuseFillDir, Pointer> conveter = helper.getNativeConveter(FuseFillDir.class);
            FuseFillDir filterFunc = conveter.fromNative(filter, helper.getFromNativeContext());
            return fuse.readdir(path, buf, filterFunc, offset, FuseFileInfo.of(fi));
        });
        fuseOperations.releasedir.set((path, fi) -> fuse.releasedir(path, FuseFileInfo.of(fi)));
        fuseOperations.fsyncdir.set((path, fi) -> fuse.fsyncdir(path, FuseFileInfo.of(fi)));
        fuseOperations.init.set(fuse::init);
        fuseOperations.destroy.set(fuse::destroy);
        fuseOperations.access.set(fuse::access);
        fuseOperations.create.set((path, mode, fi) -> fuse.create(path, mode, FuseFileInfo.of(fi)));
        fuseOperations.create.set((path, mode, fi) -> fuse.create(path, mode, FuseFileInfo.of(fi)));
        fuseOperations.ftruncate.set((path, size, fi) -> fuse.ftruncate(path, size, FuseFileInfo.of(fi)));
        fuseOperations.fgetattr.set((path, stbuf, fi) -> fuse.fgetattr(path, FileStat.of(stbuf), FuseFileInfo.of(fi)));
        fuseOperations.lock.set((path, fi, cmd, flock) -> fuse.lock(path, FuseFileInfo.of(fi), cmd, Flock.of(flock)));
        fuseOperations.utimens.set((path, timespec) -> fuse.utimens(path, new Timespec[]{Timespec.of(timespec), Timespec.of(timespec.getPointer(8))}));
        fuseOperations.bmap.set((path, blocksize, idx) -> fuse.bmap(path, blocksize, idx.getLong(0)));
        fuseOperations.ioctl.set((path, cmd, arg, fi, flags, data) -> fuse.ioctl(path, cmd, arg, FuseFileInfo.of(fi), flags, data));
        fuseOperations.poll.set((path, fi, ph, reventsp) -> fuse.poll(path, FuseFileInfo.of(fi), ph, reventsp));
        fuseOperations.write_buf.set((path, buf, off, fi) -> fuse.write_buf(path, buf, off, FuseFileInfo.of(fi)));
        fuseOperations.read_buf.set((path, bufp, size, off, fi) -> fuse.read_buf(path, bufp, size, off, FuseFileInfo.of(fi)));
        fuseOperations.flock.set((path, fi, op) -> fuse.flock(path, FuseFileInfo.of(fi), op));
        fuseOperations.fallocate.set((path, mode, off, length, fi) -> fuse.fallocate(path, mode, off, length, FuseFileInfo.of(fi)));
    }

    @Override
    public void mount(Path mountPoint) {
        if (!mounted.compareAndSet(false, true)) {
            throw new FuseException("Fuse fs already mounted!");
        }
        this.mountPoint = mountPoint;
        String[] arg = new String[]{getFSName(), "-f", "-d", mountPoint.toAbsolutePath().toString()};
        try {
            ForkJoinPool.commonPool().submit(() -> {
                System.out.println("START");
                int res = libFuse.fuse_main_real(arg.length, arg, fuseOperations, 360, null);
                System.out.println("RESULT = " + res);
                System.out.println("END");
            });
        } catch (Throwable t) {
            throw new FuseException("Unable to mount FS", t);
        }
    }

    @Override
    public void umount() {
        if (!mounted.get()) {
            throw new FuseException("Fuse fs not mounted!");
        }
        try {
            new ProcessBuilder("fusermount", "-u", "-z", mountPoint.toAbsolutePath().toString()).start();
            mounted.set(false);
        } catch (IOException e) {
            throw new FuseException("Unable to umount FS", e);
        }
    }

    protected String getFSName() {
        return "fusefs" + ThreadLocalRandom.current().nextInt();
    }
}
