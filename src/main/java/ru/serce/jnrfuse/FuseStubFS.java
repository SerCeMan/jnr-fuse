package ru.serce.jnrfuse;

import com.kenai.jffi.MemoryIO;
import jnr.ffi.*;
import jnr.ffi.Runtime;
import jnr.ffi.types.dev_t;
import jnr.ffi.types.gid_t;
import jnr.ffi.types.mode_t;
import jnr.ffi.types.off_t;
import jnr.ffi.types.size_t;
import jnr.ffi.types.u_int32_t;
import jnr.ffi.types.uid_t;
import ru.serce.jnrfuse.struct.FileStat;
import ru.serce.jnrfuse.struct.Flock;
import ru.serce.jnrfuse.struct.FuseBuf;
import ru.serce.jnrfuse.flags.FuseBufFlags;
import ru.serce.jnrfuse.struct.FuseBufvec;
import ru.serce.jnrfuse.struct.FuseFileInfo;
import ru.serce.jnrfuse.struct.FusePollhandle;
import ru.serce.jnrfuse.struct.Statvfs;
import ru.serce.jnrfuse.struct.Timespec;

public class FuseStubFS extends AbstractFuseFS {
    @Override
    @NotImplemented
    public int getattr(String path, FileStat stat) {
        return 0;
    }

    @Override
    @NotImplemented
    public int readlink(String path, Pointer buf, @size_t long size) {
        return 0;
    }

    @Override
    @NotImplemented
    public int mknod(String path, @mode_t long mode, @dev_t long rdev) {
        return create(path, mode, null);
    }

    @Override
    @NotImplemented
    public int mkdir(String path, @mode_t long mode) {
        return 0;
    }

    @Override
    @NotImplemented
    public int unlink(String path) {
        return 0;
    }

    @Override
    @NotImplemented
    public int rmdir(String path) {
        return 0;
    }

    @Override
    @NotImplemented
    public int symlink(String oldpath, String newpath) {
        return 0;
    }

    @Override
    @NotImplemented
    public int rename(String oldpath, String newpath) {
        return 0;
    }

    @Override
    @NotImplemented
    public int link(String oldpath, String newpath) {
        return 0;
    }

    @Override
    @NotImplemented
    public int chmod(String path, @mode_t long mode) {
        return 0;
    }

    @Override
    @NotImplemented
    public int chown(String path, @uid_t long uid, @gid_t long gid) {
        return 0;
    }

    @Override
    @NotImplemented
    public int truncate(String path, @off_t long size) {
        return 0;
    }

    @Override
    @NotImplemented
    public int open(String path, FuseFileInfo fi) {
        return 0;
    }

    @Override
    @NotImplemented
    public int read(String path, Pointer buf, @size_t long size, @off_t long offset, FuseFileInfo fi) {
        return 0;
    }

    @Override
    @NotImplemented
    public int write(String path, Pointer buf, @size_t long size, @off_t long offset, FuseFileInfo fi) {
        return 0;
    }

    @Override
    @NotImplemented
    public int statfs(String path, Statvfs stbuf) {
        return 0;
    }

    @Override
    @NotImplemented
    public int flush(String path, FuseFileInfo fi) {
        return 0;
    }

    @Override
    @NotImplemented
    public int release(String path, FuseFileInfo fi) {
        return 0;
    }

    @Override
    @NotImplemented
    public int fsync(String path, int isdatasync, FuseFileInfo fi) {
        return 0;
    }

    @Override
    @NotImplemented
    public int setxattr(String path, String name, Pointer value, @size_t long size, int flags) {
        return 0;
    }

    @Override
    @NotImplemented
    public int getxattr(String path, String name, Pointer value, @size_t long size) {
        return 0;
    }

    @Override
    @NotImplemented
    public int listxattr(String path, Pointer list, @size_t long size) {
        return 0;
    }

    @Override
    @NotImplemented
    public int removexattr(String path, String name) {
        return 0;
    }

    @Override
    @NotImplemented
    public int opendir(String path, FuseFileInfo fi) {
        return 0;
    }

    @Override
    @NotImplemented
    public int readdir(String path, Pointer buf, FuseFillDir filter, @off_t long offset, FuseFileInfo fi) {
        return 0;
    }

    @Override
    @NotImplemented
    public int releasedir(String path, FuseFileInfo fi) {
        return 0;
    }

    @Override
    @NotImplemented
    public int fsyncdir(String path, FuseFileInfo fi) {
        return 0;
    }

    @Override
    @NotImplemented
    public Pointer init(Pointer conn) {
        return null;
    }

    @Override
    @NotImplemented
    public void destroy(Pointer initResult) {
    }

    @Override
    @NotImplemented
    public int access(String path, int mask) {
        return 0;
    }

    @Override
    @NotImplemented
    public int create(String path, @mode_t long mode, FuseFileInfo fi) {
        return -ErrorCodes.ENOSYS();
    }

    @Override
    @NotImplemented
    public int ftruncate(String path, @off_t long size, FuseFileInfo fi) {
        return truncate(path, size);
    }

    @Override
    @NotImplemented
    public int fgetattr(String path, FileStat stbuf, FuseFileInfo fi) {
        return getattr(path, stbuf);
    }

    @Override
    @NotImplemented
    public int lock(String path, FuseFileInfo fi, int cmd, Flock flock) {
        return -ErrorCodes.ENOSYS();
    }

    @Override
    @NotImplemented
    public int utimens(String path, Timespec[] timespec) {
        return -ErrorCodes.ENOSYS();
    }

    @Override
    @NotImplemented
    public int bmap(String path, @size_t long blocksize, long idx) {
        return 0;
    }

    @Override
    @NotImplemented
    public int ioctl(String path, int cmd, Pointer arg, FuseFileInfo fi, @u_int32_t long flags, Pointer data) {
        return -ErrorCodes.ENOSYS();
    }

    @Override
    @NotImplemented
    public int poll(String path, FuseFileInfo fi, FusePollhandle ph, Pointer reventsp) {
        return -ErrorCodes.ENOSYS();
    }

    @Override
    @NotImplemented
    public int write_buf(String path, FuseBufvec buf, @off_t long off, FuseFileInfo fi) {
        // TODO.
        // Some problem in implementation, but it not enabling by default
        int res = 0;
        int size = (int) libFuse.fuse_buf_size(buf);
        FuseBuf flatbuf;
        FuseBufvec tmp = new FuseBufvec(Runtime.getSystemRuntime());
        long adr = MemoryIO.getInstance().allocateMemory(Struct.size(tmp), false);
        tmp.useMemory(Pointer.wrap(Runtime.getSystemRuntime(), adr));
        FuseBufvec.init(tmp, size);
        long mem = 0;
        if (buf.count.get() == 1 && buf.buf.flags.get() == FuseBufFlags.FUSE_BUF_IS_FD) {
            flatbuf = buf.buf;
        } else {
            res = -ErrorCodes.ENOMEM();
            mem = MemoryIO.getInstance().allocateMemory(size, false);
            if (mem == 0) {
                MemoryIO.getInstance().freeMemory(adr);
                return res;
            }
            tmp.buf.mem.set(mem);
            res = (int) libFuse.fuse_buf_copy(tmp, buf, 0);
            if (res <= 0) {
                MemoryIO.getInstance().freeMemory(adr);
                MemoryIO.getInstance().freeMemory(mem);
                return res;
            }
            tmp.buf.size.set(res);
            flatbuf = tmp.buf;
        }
        res = write(path, flatbuf.mem.get(), flatbuf.size.get(), off, fi);
        if (mem != 0) {
            MemoryIO.getInstance().freeMemory(adr);
            MemoryIO.getInstance().freeMemory(mem);
        }
        return res;
    }

    @Override
    @NotImplemented
    public int read_buf(String path, Pointer bufp, @size_t long size, @off_t long off, FuseFileInfo fi) {
        // should be implemented or null
        long vecmem = MemoryIO.getInstance().allocateMemory(Struct.size(new FuseBufvec(Runtime.getSystemRuntime())), false);
        if (vecmem == 0) {
            return -ErrorCodes.ENOMEM();
        }
        Pointer src = Pointer.wrap(Runtime.getSystemRuntime(), vecmem);
        long memAdr = MemoryIO.getInstance().allocateMemory(size, false);
        if (memAdr == 0) {
            MemoryIO.getInstance().freeMemory(vecmem);
            return -ErrorCodes.ENOMEM();
        }
        Pointer mem = Pointer.wrap(Runtime.getSystemRuntime(), memAdr);
        FuseBufvec buf = FuseBufvec.of(src);
        FuseBufvec.init(buf, size);
        buf.buf.mem.set(mem);
        bufp.putAddress(0, src.address());
        int res = read(path, mem, size, off, fi);
        if (res >= 0)
            buf.buf.size.set(res);
        return res;
    }

    @Override
    @NotImplemented
    public int flock(String path, FuseFileInfo fi, int op) {
        return -ErrorCodes.ENOSYS();
    }

    @Override
    @NotImplemented
    public int fallocate(String path, int mode, @off_t long off, @off_t long length, FuseFileInfo fi) {
        return -ErrorCodes.ENOSYS();
    }
}
