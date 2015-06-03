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
import ru.serce.jnrfuse.struct.FuseBufvec;
import ru.serce.jnrfuse.struct.FuseFileInfo;
import ru.serce.jnrfuse.struct.FusePollhandle;
import ru.serce.jnrfuse.struct.Statvfs;
import ru.serce.jnrfuse.struct.Timespec;

import java.lang.String;


/**
 * Fuse file system.
 * All documentation from "fuse.h"
 *
 * @see <fuse.h>
 *
 * Most of these should work very similarly to the well known UNIX
 * file system operations.  A major exception is that instead of
 * returning an error in 'errno', the operation should return the
 * negated error value (-errno) directly.
 *
 * All methods are optional, but some are essential for a useful
 * filesystem (e.g. getattr).  Open, flush, release, fsync, opendir,
 * releasedir, fsyncdir, access, create, ftruncate, fgetattr, lock,
 * init and destroy are special purpose methods, without which a full
 * featured filesystem can still be implemented.
 *
 * See http://fuse.sourceforge.net/wiki/ for more information.
 *
 * @author Sergey Tselovalnikov
 * @since 27.05.15
 */
public interface FuseFS extends Mountable {
    /** Get file attributes.
     *
     * Similar to stat().  The 'st_dev' and 'st_blksize' fields are
     * ignored.	 The 'st_ino' field is ignored except if the 'use_ino'
     * mount option is given.
     */
    int getattr(String path, FileStat stat);

    /** Read the target of a symbolic link
     *
     * The buffer should be filled with a null terminated string.  The
     * buffer size argument includes the space for the terminating
     * null character.	If the linkname is too long to fit in the
     * buffer, it should be truncated.	The return value should be 0
     * for success.
     */
    int readlink(String path, Pointer buf, @size_t long size);

    /** Create a file node
     *
     * This is called for creation of all non-directory, non-symlink
     * nodes.  If the filesystem defines a create() method, then for
     * regular files that will be called instead.
     */
    int mknod(String path, @mode_t long mode, @dev_t long rdev);

    /** Create a directory
     *
     * Note that the mode argument may not have the type specification
     * bits set, i.e. S_ISDIR(mode) can be false.  To obtain the
     * correct directory type bits use  mode|S_IFDIR
     * */
    int mkdir(String path, @mode_t long mode);

    /** Remove a file */
    int unlink(String path);

    /** Remove a directory */
    int rmdir(String path);

    /** Create a symbolic link */
    int symlink(String oldpath, String newpath);

    /** Rename a file */
    int rename(String oldpath, String newpath);

    /** Create a hard link to a file */
    int link(String oldpath, String newpath);

    /** Change the permission bits of a file */
    int chmod(String path, @mode_t long mode);

    /** Change the owner and group of a file */
    int chown(String path, @uid_t long uid, @gid_t long gid);

    /** Change the size of a file */
    int truncate(String path, @off_t long size);

    /** File open operation
     *
     * No creation (O_CREAT, O_EXCL) and by default also no
     * truncation (O_TRUNC) flags will be passed to open(). If an
     * application specifies O_TRUNC, fuse first calls truncate()
     * and then open(). Only if 'atomic_o_trunc' has been
     * specified and kernel version is 2.6.24 or later, O_TRUNC is
     * passed on to open.
     *
     * Unless the 'default_permissions' mount option is given,
     * open should check if the operation is permitted for the
     * given flags. Optionally open may also return an arbitrary
     * filehandle in the fuse_file_info structure, which will be
     * passed to all file operations.
     */
    int open(String path, FuseFileInfo fi);

    /** Read data from an open file
     *
     * Read should return exactly the number of bytes requested except
     * on EOF or error, otherwise the rest of the data will be
     * substituted with zeroes.	 An exception to this is when the
     * 'direct_io' mount option is specified, in which case the return
     * value of the read system call will reflect the return value of
     * this operation.
     */
    int read(String path, Pointer buf, @size_t long size, @off_t long offset, FuseFileInfo fi);

    /** Write data to an open file
     *
     * Write should return exactly the number of bytes requested
     * except on error.	 An exception to this is when the 'direct_io'
     * mount option is specified (see read operation).
     */
    int write(String path, Pointer buf, @size_t long size, @off_t long offset, FuseFileInfo fi);

    /** Get file system statistics
     *
     * The 'f_frsize', 'f_favail', 'f_fsid' and 'f_flag' fields are ignored
     */
    int statfs(String path, Statvfs stbuf);

    /** Possibly flush cached data
     *
     * BIG NOTE: This is not equivalent to fsync().  It's not a
     * request to sync dirty data.
     *
     * Flush is called on each close() of a file descriptor.  So if a
     * filesystem wants to return write errors in close() and the file
     * has cached dirty data, this is a good place to write back data
     * and return any errors.  Since many applications ignore close()
     * errors this is not always useful.
     *
     * NOTE: The flush() method may be called more than once for each
     * open().	This happens if more than one file descriptor refers
     * to an opened file due to dup(), dup2() or fork() calls.	It is
     * not possible to determine if a flush is final, so each flush
     * should be treated equally.  Multiple write-flush sequences are
     * relatively rare, so this shouldn't be a problem.
     *
     * Filesystems shouldn't assume that flush will always be called
     * after some writes, or that if will be called at all.
     */
    int flush(String path, FuseFileInfo fi);

    /** Release an open file
     *
     * Release is called when there are no more references to an open
     * file: all file descriptors are closed and all memory mappings
     * are unmapped.
     *
     * For every open() call there will be exactly one release() call
     * with the same flags and file descriptor.	 It is possible to
     * have a file opened more than once, in which case only the last
     * release will mean, that no more reads/writes will happen on the
     * file.  The return value of release is ignored.
     */
    int release(String path, FuseFileInfo  fi);

    /** Synchronize file contents
     *
     * If the datasync parameter is non-zero, then only the user data
     * should be flushed, not the meta data.
     */
    int fsync(String path, int isdatasync, FuseFileInfo fi);

    /** Set extended attributes */
    int setxattr(String path, String name,  Pointer value, @size_t long size, int flags);

    /** Get extended attributes */
    int getxattr(String path, String name, Pointer value, @size_t long size);

    /** List extended attributes
     *
     * The retrieved list is placed
     * in list, a caller-allocated buffer whose size (in bytes) is specified
     * in the argument size.  The list is the set of (null-terminated)
     * names, one after the other.  Names of extended attributes to which
     * the calling process does not have access may be omitted from the
     * list. The length of the attribute name list is returned
     */
    int listxattr(String path, Pointer list, @size_t long size);

    /** Remove extended attributes */
    int removexattr(String path, String name);

    /** Open directory
     *
     * Unless the 'default_permissions' mount option is given,
     * this method should check if opendir is permitted for this
     * directory. Optionally opendir may also return an arbitrary
     * filehandle in the fuse_file_info structure, which will be
     * passed to readdir, closedir and fsyncdir.
     */
    int opendir(String path, FuseFileInfo fi);

    /** Read directory
     *
     * This supersedes the old getdir() interface.  New applications
     * should use this.
     *
     * The filesystem may choose between two modes of operation:
     *
     * 1) The readdir implementation ignores the offset parameter, and
     * passes zero to the filler function's offset.  The filler
     * function will not return '1' (unless an error happens), so the
     * whole directory is read in a single readdir operation.  This
     * works just like the old getdir() method.
     *
     * 2) The readdir implementation keeps track of the offsets of the
     * directory entries.  It uses the offset parameter and always
     * passes non-zero offset to the filler function.  When the buffer
     * is full (or an error happens) the filler function will return
     * '1'.
     */
    int readdir(String path, Pointer buf, FuseFillDir filter, @off_t long offset, FuseFileInfo fi);

    /** Release directory
     */
    int releasedir(String path, FuseFileInfo fi);

    /** Synchronize directory contents
     *
     * If the datasync parameter is non-zero, then only the user data
     * should be flushed, not the meta data
     */
    int fsyncdir(String path, FuseFileInfo fi);

    /**
     * Initialize filesystem
     *
     * The return value will passed in the private_data field of
     * fuse_context to all file operations and as a parameter to the
     * destroy() method.
     */
    Pointer init(Pointer conn);

    /**
     * Clean up filesystem
     *
     * Called on filesystem exit.
     */
    void destroy(Pointer initResult);

    /**
     * Check file access permissions
     *
     * This will be called for the access() system call.  If the
     * 'default_permissions' mount option is given, this method is not
     * called.
     *
     * This method is not called under Linux kernel versions 2.4.x
     */
    int access(String path, int mask);

    /**
     * Create and open a file
     *
     * If the file does not exist, first create it with the specified
     * mode, and then open it.
     *
     * If this method is not implemented or under Linux kernel
     * versions earlier than 2.6.15, the mknod() and open() methods
     * will be called instead.
     */
    int create(String path, @mode_t long mode, FuseFileInfo fi);

    /**
     * Change the size of an open file
     *
     * This method is called instead of the truncate() method if the
     * truncation was invoked from an ftruncate() system call.
     *
     * If this method is not implemented or under Linux kernel
     * versions earlier than 2.6.15, the truncate() method will be
     * called instead.
     */
    int ftruncate(String path, @off_t long size, FuseFileInfo fi);

    /**
     * Get attributes from an open file
     *
     * This method is called instead of the getattr() method if the
     * file information is available.
     *
     * Currently this is only called after the create() method if that
     * is implemented (see above).  Later it may be called for
     * invocations of fstat() too.
     */
    int fgetattr(String path, FileStat stbuf, FuseFileInfo fi);

    /**
     * Perform POSIX file locking operation
     *
     * The cmd argument will be either F_GETLK, F_SETLK or F_SETLKW.
     *
     * For the meaning of fields in 'struct flock' see the man page
     * for fcntl(2).  The l_whence field will always be set to
     * SEEK_SET.
     *
     * For checking lock ownership, the 'fuse_file_info->owner'
     * argument must be used.
     *
     * For F_GETLK operation, the library will first check currently
     * held locks, and if a conflicting lock is found it will return
     * information without calling this method.	 This ensures, that
     * for local locks the l_pid field is correctly filled in.	The
     * results may not be accurate in case of race conditions and in
     * the presence of hard links, but it's unlikely that an
     * application would rely on accurate GETLK results in these
     * cases.  If a conflicting lock is not found, this method will be
     * called, and the filesystem may fill out l_pid by a meaningful
     * value, or it may leave this field zero.
     *
     * For F_SETLK and F_SETLKW the l_pid field will be set to the pid
     * of the process performing the locking operation.
     *
     * Note: if this method is not implemented, the kernel will still
     * allow file locking to work locally.  Hence it is only
     * interesting for network filesystems and similar.
     */
    int lock(String path, FuseFileInfo fi, int cmd, Flock flock);

    /**
     * Change the access and modification times of a file with
     * nanosecond resolution
     *
     * This supersedes the old utime() interface.  New applications
     * should use this.
     *
     * See the utimensat(2) man page for details.
     */
    int utimens(String path, Timespec[] timespec);

    /**
     * Map block index within file to block index within device
     *
     * Note: This makes sense only for block device backed filesystems
     * mounted with the 'blkdev' option
     */
    int bmap(String path, @size_t long blocksize, long idx);

    /**
     * Ioctl
     *
     * flags will have FUSE_IOCTL_COMPAT set for 32bit ioctls in
     * 64bit environment.  The size and direction of data is
     * determined by _IOC_*() decoding of cmd.  For _IOC_NONE,
     * data will be NULL, for _IOC_WRITE data is out area, for
     * _IOC_READ in area and if both are set in/out area.  In all
     * non-NULL cases, the area is of _IOC_SIZE(cmd) bytes.
     */
    int ioctl(String path, int cmd, Pointer arg, FuseFileInfo fi, @u_int32_t long flags, Pointer data);

    /**
     * Poll for IO readiness events
     *
     * Note: If ph is non-NULL, the client should notify
     * when IO readiness events occur by calling
     * fuse_notify_poll() with the specified ph.
     *
     * Regardless of the number of times poll with a non-NULL ph
     * is received, single notification is enough to clear all.
     * Notifying more times incurs overhead but doesn't harm
     * correctness.
     *
     * The callee is responsible for destroying ph with
     * fuse_pollhandle_destroy() when no longer in use.
     *
     * @param reventsp A pointer to a bitmask of  the  returned  events satisfied.
     */
    int poll(String path, FuseFileInfo fi, FusePollhandle ph, Pointer reventsp);

    /** Write contents of buffer to an open file
     *
     * Similar to the write() method, but data is supplied in a
     * generic buffer.  Use fuse_buf_copy() to transfer data to
     * the destination.
     */
    int write_buf(String path, FuseBufvec buf, @off_t long off, FuseFileInfo fi);

    /** Store data from an open file in a buffer
     *
     * Similar to the read() method, but data is stored and
     * returned in a generic buffer.
     *
     * No actual copying of data has to take place, the source
     * file descriptor may simply be stored in the buffer for
     * later data transfer.
     *
     * The buffer must be allocated dynamically and stored at the
     * location pointed to by bufp.  If the buffer contains memory
     * regions, they too must be allocated using malloc().  The
     * allocated memory will be freed by the caller.
     */
    int read_buf(String path, Pointer bufp, @size_t long size, @off_t long off, FuseFileInfo fi);

    /**
     * Perform BSD file locking operation
     *
     * The op argument will be either LOCK_SH, LOCK_EX or LOCK_UN
     *
     * Nonblocking requests will be indicated by ORing LOCK_NB to
     * the above operations
     *
     * For more information see the flock(2) manual page.
     *
     * Additionally fi->owner will be set to a value unique to
     * this open file.  This same value will be supplied to
     * ->release() when the file is released.
     *
     * Note: if this method is not implemented, the kernel will still
     * allow file locking to work locally.  Hence it is only
     * interesting for network filesystems and similar.
     */
    int flock(String path, FuseFileInfo fi, int op);

    /**
     * Allocates space for an open file
     *
     * This function ensures that required space is allocated for specified
     * file.  If this function returns success then any subsequent write
     * request to specified range is guaranteed not to fail because of lack
     * of space on the file system media.
     */
    int fallocate(String path, int mode, @off_t long off, @off_t long length, FuseFileInfo fi);
}
