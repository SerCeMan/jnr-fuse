package ru.serce.jfuse;

import com.kenai.jffi.Closure.Buffer;
import jdk.internal.org.objectweb.asm.Type;
import jnr.ffi.NativeType;
import jnr.ffi.Runtime;
import jnr.ffi.Struct;
import jnr.ffi.annotations.Delegate;
import jnr.posix.LinuxFileStat64;

public class FuseOperations extends Struct {
    public FuseOperations(Runtime runtime) {
        super(runtime);
    }

    public void setReaddir(ReadDirCallback readdir) {
        this.readdir.set(readdir);
    }

    public void setOpen(OpenCallback open) {
        this.open.set(open);
    }

    public void setRead(ReadCallback read) {
        this.read.set(read);
    }

    public static interface GetAttrCallback {
        @Delegate
        public int getattr(java.lang.String path,  jnr.ffi.Pointer stbuf);
    }

    public static interface ReadDirCallback {
        @Delegate
        public int readdir(java.lang.String path, jnr.ffi.Pointer buf, jnr.ffi.Pointer filter, @jnr.ffi.types.off_t int offset, jnr.ffi.Pointer fi);
    }

    public static interface OpenCallback {
        @Delegate
        public int open(java.lang.String path, jnr.ffi.Pointer fi);
    }

    public static interface ReadCallback {
        @Delegate
        public int read(java.lang.String path, jnr.ffi.Pointer buf, long size, long offset, jnr.ffi.Pointer fi);
    }

    public void setGetAttr(GetAttrCallback callback) {
        getattr.set(callback);
    }

    Function<GetAttrCallback> getattr = function(GetAttrCallback.class);
    Function<Pointer> readlink;
    Function<Pointer> getdir;
    Function<Pointer> mknod;
    Function<Pointer> mkdir;
    Function<Pointer> unlink;
    Function<Pointer> rmdir;
    Function<Pointer> symlink;

    Function<Pointer> rename;
    Function<Pointer> link;
    Function<Pointer> chmod;
    Function<Pointer> chown;
    Function<Pointer> truncate;
    Function<Pointer> utime;
    Function<OpenCallback> open = function(OpenCallback.class);
    Function<ReadCallback> read = function(ReadCallback.class);
    Function<Pointer> write;
    Function<Pointer> statfs;
    Function<Pointer> flush;
    Function<Pointer> release;
    Function<Pointer> fsync;
    Function<Pointer> setxattr;
    Function<Pointer> getxattr;
    Function<Pointer> listxattr;
    Function<Pointer> removexattr;
    Function<Pointer> opendir;
    Function<ReadDirCallback> readdir = function(ReadDirCallback.class);
    Function<Pointer> releasedir;
    Function<Pointer> fsyncdir;
    Function<Pointer> init;
    Function<Pointer> destroy;
    Function<Pointer> access;
    Function<Pointer> create;
    Function<Pointer> ftruncate;
    Function<Pointer> fgetattr;
    Function<Pointer> lock;
    Function<Pointer> utimens;
    Function<Pointer> bmap;


    Padding flag_nullpath_ok = new Padding(NativeType.UINT, 1);
    Padding flag_nopath = new Padding(NativeType.UINT, 1);
    Padding flag_utime_omit_ok = new Padding(NativeType.UINT, 1);
    Padding flag_reserved = new Padding(NativeType.UINT, 29);

    Function<Pointer> ioctl;
    Function<Pointer> poll;
    Function<Pointer> write_buf;
    Function<Pointer> read_buf;
    Function<Pointer> flock;
    Function<Pointer> fallocate;
}
