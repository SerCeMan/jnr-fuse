package ru.serce.jfuse;

import jnr.ffi.Pointer;
import jnr.ffi.types.dev_t;
import jnr.ffi.types.gid_t;
import jnr.ffi.types.mode_t;
import jnr.ffi.types.off_t;
import jnr.ffi.types.size_t;
import jnr.ffi.types.u_int32_t;
import jnr.ffi.types.uid_t;
import ru.serce.jfuse.struct.FileStat;
import ru.serce.jfuse.struct.Flock;
import ru.serce.jfuse.struct.FuseFileInfo;
import ru.serce.jfuse.struct.Statvfs;
import ru.serce.jfuse.struct.Timespec;

import java.lang.String;
import java.nio.ByteBuffer;

/**
 * Fuse file system.
 *
 * @see <fuse.h>
 *
 * @author Sergey Tselovalnikov
 * @since 27.05.15
 */
public interface FuseFS extends Mountable {
    int getattr(String path, FileStat stat);

    int readlink(String path, Pointer buf, @size_t long size);

    int mknod(String path, @mode_t long mode, @dev_t long rdev);

    int mkdir(String path, @mode_t long mode);

    int unlink(String path);

    int rmdir(String path);

    int symlink(String oldpath, String newpath);

    int rename(String oldpath, String newpath);

    int link(String oldpath, String newpath);

    int chmod(String path, @mode_t long mode);

    int chown(String path, @uid_t long uid, @gid_t long gid);

    int truncate(String path, @off_t long size);

    int open(String path, FuseFileInfo fi);

    int read(String path, Pointer buf, @size_t long size, @off_t long offset, FuseFileInfo fi);

    int write(String path, Pointer buf, @size_t long size, @off_t long offset, FuseFileInfo fi);

    int statfs(String path, Statvfs stbuf);

    int flush(String path, FuseFileInfo fi);

    int release(String path, FuseFileInfo  fi);

    int fsync(String path, int isdatasync, FuseFileInfo fi);

    int setxattr(String path, String name,  Pointer value, @size_t long size, int flags);

    int getxattr(String path, String name, Pointer value, @size_t long size);

    int listxattr(String path, String list, @size_t long size);

    int removexattr(String path, String name);

    int opendir(String path, FuseFileInfo fi);

    int readdir(String path, Pointer buf, FuseFillDir filter, @off_t long offset, FuseFileInfo fi);

    int releasedir(String path, FuseFileInfo fi);

    int fsyncdir(String path, FuseFileInfo fi);

    Pointer init(Pointer conn);

    void destroy(Pointer initResult);

    int access(String path, int mask);

    int create(String path, @mode_t long mode, FuseFileInfo fi);

    int ftruncate(String path, @off_t long size, FuseFileInfo fi);

    int fgetattr(String path, FileStat stbuf, FuseFileInfo fi);

    int lock(String path, FuseFileInfo fi, int cmd, Flock flock);

    int utimens(String path, Timespec[] timespec);

    int bmap(String path, @size_t long blocksize, long idx);

    int ioctl(String path, int cmd, Pointer arg, FuseFileInfo fi, @u_int32_t long flags, Pointer data);

    int poll(String path, FuseFileInfo fi, Pointer ph, Pointer reventsp);

    // TODO buf
    int write_buf(String path, Pointer buf, @off_t long off, FuseFileInfo fi);

    // TODO fuse_bufvec
    int read_buf(String path, Pointer bufp, @size_t long size, @off_t long off, FuseFileInfo fi);

    int flock(String path, FuseFileInfo fi, int op);

    int fallocate(String path, int mode, @off_t long off, @off_t long length, FuseFileInfo fi);






}
