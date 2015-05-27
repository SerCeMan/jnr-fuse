package ru.serce.jfuse;

import jnr.ffi.Pointer;
import jnr.ffi.annotations.Delegate;
import jnr.ffi.types.dev_t;
import jnr.ffi.types.gid_t;
import jnr.ffi.types.mode_t;
import jnr.ffi.types.off_t;
import jnr.ffi.types.size_t;
import jnr.ffi.types.u_int32_t;
import jnr.ffi.types.uid_t;

import java.lang.String;

public final class FuseCallbacks {

    public interface GetAttrCallback {
        @Delegate
        int getattr(String path, Pointer stbuf);
    }

    public interface ReadlinkCallback {
        @Delegate
        int readlink(String path, Pointer buf, @size_t long size);
    }

    public interface MknodCallback {
        @Delegate
        int mknod(String path, @mode_t long mode, @dev_t long rdev);
    }

    public interface MkdirCallback {
        @Delegate
        int mkdir(String path, @mode_t long mode);
    }

    public interface UnlinkCallback {
        @Delegate
        int unlink(String path);
    }

    public interface RmdirCallback {
        @Delegate
        int rmdir(String path);
    }

    public interface SymlinkCallback {
        @Delegate
        int symlink(String oldpath, String newpath);
    }

    public interface RenameCallback {
        @Delegate
        int rename(String oldpath, String newpath);
    }

    public interface LinkCallback {
        @Delegate
        int link(String oldpath, String newpath);
    }

    public interface ChmodCallback {
        @Delegate
        int chmod(String path, @mode_t long mode);
    }

    public interface ChownCallback {
        @Delegate
        int chown(String path, @uid_t long uid, @gid_t long gid);
    }

    public interface TruncateCallback {
        @Delegate
        int truncate(String path, @off_t long size);
    }

    public interface OpenCallback {
        @Delegate
        int open(String path, Pointer fi);
    }

    public interface ReadCallback {
        @Delegate
        int read(String path, Pointer buf, @size_t long size, @off_t long offset, Pointer fi);
    }

    public interface WriteCallback {
        @Delegate
        int write(String path, Pointer buf, @size_t long size, @off_t long offset, Pointer fi);
    }

    public interface StatfsCallback {
        @Delegate
        int statfs(String path, Pointer stbuf);
    }

    public interface FlushCallback {
        @Delegate
        int flush(String path, Pointer fi);
    }

    public interface ReleaseCallback {
        @Delegate
        int release(String path, Pointer fi);
    }

    public interface FsyncCallback {
        @Delegate
        int fsync(String path, int isdatasync, Pointer fi);
    }

    public interface SetxattrCallback {
        @Delegate
        int setxattr(String path, String name, Pointer value, @size_t long size, int flags);
    }

    public interface GetxattrCallback {
        @Delegate
        int getxattr(String path, String name, Pointer value, @size_t long size);
    }

    public interface ListxattrCallback {
        @Delegate
        int listxattr(String path, String list, @size_t long size);
    }

    public interface RemovexattrCallback {
        @Delegate
        int removexattr(String path, String name);
    }

    public interface OpendirCallback {
        @Delegate
        int opendir(String path, Pointer fi);
    }

    public interface ReaddirCallback {
        @Delegate
        int readdir(String path, Pointer buf, Pointer filter, @off_t long offset, Pointer fi);
    }

    public interface ReleasedirCallback {
        @Delegate
        int releasedir(String path, Pointer fi);
    }

    public interface FsyncdirCallback {
        @Delegate
        int fsyncdir(String path, Pointer fi);
    }

    public interface InitCallback {
        @Delegate
        public Pointer init(Pointer conn);
    }

    public interface DestroyCallback {
        @Delegate
        void destroy(Pointer initResult);
    }

    public interface AccessCallback {
        @Delegate
        void access(String path, int mask);
    }

    public interface CreateCallback {
        @Delegate
        void create(String path, @mode_t long mode, Pointer fi);
    }

    public interface FtruncateCallback {
        @Delegate
        void ftruncate(String path, @off_t long size, Pointer fi);
    }

    public interface FgetattrCallback {
        @Delegate
        void fgetattr(String path, Pointer stbuf, Pointer fi);
    }

    public interface LockCallback {
        @Delegate
        void lock(String path, Pointer fi, int cmd, Pointer flock);
    }

    public interface UtimensCallback {
        @Delegate
        void utimens(String path, Pointer timespec);
    }

    public interface BmapCallback {
        @Delegate
        void bmap(String path, @size_t long blocksize, Pointer idx);
    }


    public interface IoctlCallback {
        @Delegate
        void ioctl(String path, int cmd, Pointer arg, Pointer fi, @u_int32_t long flags, Pointer data);
    }

    public interface PollCallback {
        @Delegate
        void poll(String path, Pointer fi, Pointer ph, Pointer reventsp);
    }

    public interface WritebufCallback {
        @Delegate
        void write_buf(String path, Pointer buf, @off_t long off, Pointer fi);
    }

    public interface ReadbufCallback {
        @Delegate
        void read_buf(String path, Pointer bufp, @size_t long size, @off_t long off, Pointer fi);
    }

    public interface FlockCallback {
        @Delegate
        void flock(String path, Pointer fi, int op);
    }

    public interface FallocateCallback {
        @Delegate
        void fallocate(String path, int mode, @off_t long off, @off_t long length, Pointer fi);
    }

    private FuseCallbacks() {
    }
}
