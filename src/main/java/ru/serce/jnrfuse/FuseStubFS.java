package ru.serce.jnrfuse;

import jnr.ffi.Pointer;
import jnr.ffi.types.dev_t;
import jnr.ffi.types.gid_t;
import jnr.ffi.types.mode_t;
import jnr.ffi.types.off_t;
import jnr.ffi.types.size_t;
import jnr.ffi.types.u_int32_t;
import jnr.ffi.types.uid_t;
import ru.serce.jnrfuse.struct.FileStat;
import ru.serce.jnrfuse.struct.Flock;
import ru.serce.jnrfuse.struct.FuseFileInfo;
import ru.serce.jnrfuse.struct.Statvfs;
import ru.serce.jnrfuse.struct.Timespec;

public class FuseStubFS extends AbstractFuseFS {
    @Override
    public int getattr(String path, FileStat stat) {
        return 0;
    }

    @Override
    public int readlink(String path, Pointer buf, @size_t long size) {
        return 0;
    }

    @Override
    public int mknod(String path, @mode_t long mode, @dev_t long rdev) {
        return create(path, mode, null);
    }

    @Override
    public int mkdir(String path, @mode_t long mode) {
        return 0;
    }

    @Override
    public int unlink(String path) {
        return 0;
    }

    @Override
    public int rmdir(String path) {
        return 0;
    }

    @Override
    public int symlink(String oldpath, String newpath) {
        return 0;
    }

    @Override
    public int rename(String oldpath, String newpath) {
        return 0;
    }

    @Override
    public int link(String oldpath, String newpath) {
        return 0;
    }

    @Override
    public int chmod(String path, @mode_t long mode) {
        return 0;
    }

    @Override
    public int chown(String path, @uid_t long uid, @gid_t long gid) {
        return 0;
    }

    @Override
    public int truncate(String path, @off_t long size) {
        return 0;
    }

    @Override
    public int open(String path, FuseFileInfo fi) {
        return 0;
    }

    @Override
    public int read(String path, Pointer buf, @size_t long size, @off_t long offset, FuseFileInfo fi) {
        return 0;
    }

    @Override
    public int write(String path, Pointer buf, @size_t long size, @off_t long offset, FuseFileInfo fi) {
        return 0;
    }

    @Override
    public int statfs(String path, Statvfs stbuf) {
        return 0;
    }

    @Override
    public int flush(String path, FuseFileInfo fi) {
        return 0;
    }

    @Override
    public int release(String path, FuseFileInfo fi) {
        return 0;
    }

    @Override
    public int fsync(String path, int isdatasync, FuseFileInfo fi) {
        return 0;
    }

    @Override
    public int setxattr(String path, String name, Pointer value, @size_t long size, int flags) {
        return 0;
    }

    @Override
    public int getxattr(String path, String name, Pointer value, @size_t long size) {
        return 0;
    }

    @Override
    public int listxattr(String path, String list, @size_t long size) {
        return 0;
    }

    @Override
    public int removexattr(String path, String name) {
        return 0;
    }

    @Override
    public int opendir(String path, FuseFileInfo fi) {
        return 0;
    }

    @Override
    public int readdir(String path, Pointer buf, FuseFillDir filter, @off_t long offset, FuseFileInfo fi) {
        return 0;
    }

    @Override
    public int releasedir(String path, FuseFileInfo fi) {
        return 0;
    }

    @Override
    public int fsyncdir(String path, FuseFileInfo fi) {
        return 0;
    }

    @Override
    public Pointer init(Pointer conn) {
        return null;
    }

    @Override
    public void destroy(Pointer initResult) {

    }

    @Override
    public int access(String path, int mask) {
        return 0;
    }

    @Override
    public int create(String path, @mode_t long mode, FuseFileInfo fi) {
        return 0;
    }

    @Override
    public int ftruncate(String path, @off_t long size, FuseFileInfo fi) {
        return truncate(path, size);
    }

    @Override
    public int fgetattr(String path, FileStat stbuf, FuseFileInfo fi) {
        return getattr(path, stbuf);
    }

    @Override
    public int lock(String path, FuseFileInfo fi, int cmd, Flock flock) {
        return 0;
    }

    @Override
    public int utimens(String path, Timespec[] timespec) {
        return 0;
    }

    @Override
    public int bmap(String path, @size_t long blocksize, long idx) {
        return 0;
    }

    @Override
    public int ioctl(String path, int cmd, Pointer arg, FuseFileInfo fi, @u_int32_t long flags, Pointer data) {
        return 0;
    }

    @Override
    public int poll(String path, FuseFileInfo fi, Pointer ph, Pointer reventsp) {
        return 0;
    }

    @Override
    public int write_buf(String path, Pointer buf, @off_t long off, FuseFileInfo fi) {
        return 0;
    }

    @Override
    public int read_buf(String path, Pointer bufp, @size_t long size, @off_t long off, FuseFileInfo fi) {
        return 0;
    }

    @Override
    public int flock(String path, FuseFileInfo fi, int op) {
        return 0;
    }

    @Override
    public int fallocate(String path, int mode, @off_t long off, @off_t long length, FuseFileInfo fi) {
        return 0;
    }
}
