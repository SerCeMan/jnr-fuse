package ru.serce.jfuse;

import jnr.ffi.NativeType;
import jnr.ffi.Runtime;
import jnr.ffi.Struct;
import jnr.ffi.annotations.Delegate;
import jnr.ffi.provider.IntPointer;

import java.util.concurrent.locks.Lock;

public class FuseOperations extends Struct {

    public class PublicFunction<T> extends AbstractMember {
        private final Class<? extends T> closureClass;
        private T instance;

        public PublicFunction(Class<? extends T> closureClass) {
            super(NativeType.ADDRESS);
            this.closureClass = closureClass;
        }

        public final void set(T value) {
            getMemory().putPointer(offset(), getRuntime().getClosureManager().getClosurePointer(closureClass, instance = value));
        }
    }

    protected final <T> PublicFunction<T> pubFunction(Class<T> closureClass) {
        return new PublicFunction<>(closureClass);
    }
    
    
    public FuseOperations(Runtime runtime) {
        super(runtime);
    }

    public void setRename(RenameCallback callback) {
        rename.set(callback);
    }

    public void setLink(LinkCallback callback) {
        link.set(callback);
    }

    public void setChmod(ChmodCallback callback) {
        chmod.set(callback);
    }
    
    




    public static interface GetAttrCallback {
        @Delegate
        public int getattr(java.lang.String path, jnr.ffi.Pointer stbuf);

    }

    public static interface OpenCallback {
        @Delegate
        public int open(java.lang.String path, jnr.ffi.Pointer fi);

    }

    public static interface ReadCallback {
        @Delegate
        public int read(java.lang.String path, jnr.ffi.Pointer buf, @jnr.ffi.types.size_t long size,
                        @jnr.ffi.types.off_t long offset, jnr.ffi.Pointer fi);
    }

    public static interface ReadlinkCallback {
        @Delegate
        public int readlink(java.lang.String path, jnr.ffi.Pointer buf, @jnr.ffi.types.size_t long size);
    }

    public static interface MknodCallback {
        @Delegate
        public int mknod(java.lang.String path, @jnr.ffi.types.mode_t long mode, @jnr.ffi.types.dev_t long rdev);
    }

    public static interface MkdirCallback {
        @Delegate
        public int mkdir(java.lang.String path, @jnr.ffi.types.mode_t long mode);
    }

    public static interface UnlinkCallback {
        @Delegate
        public int unlink(java.lang.String path);
    }

    public static interface RmdirCallback {
        @Delegate
        public int rmdir(java.lang.String path);
    }

    public static interface SymlinkCallback {
        @Delegate
        public int symlink(java.lang.String oldpath, java.lang.String newpath);
    }

    public static interface RenameCallback {
        @Delegate
        public int rename(java.lang.String oldpath, java.lang.String newpath);
    }

    public static interface LinkCallback {
        @Delegate
        public int link(java.lang.String oldpath, java.lang.String newpath);
    }

    public static interface ChmodCallback {
        @Delegate
        public int chmod(java.lang.String path, @jnr.ffi.types.mode_t long mode);
    }

    public static interface ChownCallback {
        @Delegate
        public int chown(java.lang.String path, @jnr.ffi.types.uid_t long uid, @jnr.ffi.types.gid_t long gid);
    }

    public static interface TruncateCallback {
        @Delegate
        public int truncate(java.lang.String path, @jnr.ffi.types.off_t long size);
    }

    public static interface WriteCallback {
        @Delegate
        public int write(java.lang.String path, java.lang.String buf, @jnr.ffi.types.size_t long size,
                         @jnr.ffi.types.off_t long offset, jnr.ffi.Pointer fi);
    }

    public static interface StatfsCallback {
        @Delegate
        public int statfs(java.lang.String path, jnr.ffi.Pointer stbuf);
    }

    public static interface FlushCallback {
        @Delegate
        public int flush(java.lang.String path, jnr.ffi.Pointer fi);
    }

    public static interface ReleaseCallback {
        @Delegate
        public int release(java.lang.String path, jnr.ffi.Pointer fi);
    }

    public static interface FsyncCallback {
        @Delegate
        public int fsync(java.lang.String path, int isdatasync, jnr.ffi.Pointer fi);
    }

    public static interface SetxattrCallback {
        @Delegate
        public int setxattr(java.lang.String path, java.lang.String name, jnr.ffi.Pointer value,
                            @jnr.ffi.types.size_t long size, int flags);
    }

    public static interface GetxattrCallback {
        @Delegate
        public int getxattr(java.lang.String path, java.lang.String name, jnr.ffi.Pointer value, @jnr.ffi.types.size_t long size);
    }

    public static interface ListxattrCallback {
        @Delegate
        public int listxattr(java.lang.String path, java.lang.String list, @jnr.ffi.types.size_t long size);
    }


    public static interface RemovexattrCallback {
        @Delegate
        public int removexattr(java.lang.String path, java.lang.String name);
    }

    public static interface OpendirCallback {
        @Delegate
        public int opendir(java.lang.String path, jnr.ffi.Pointer fi);
    }


    public static interface ReaddirCallback {
        @Delegate
        public int readdir(java.lang.String path, jnr.ffi.Pointer buf, jnr.ffi.Pointer filter, @jnr.ffi.types.off_t long offset, jnr.ffi.Pointer fi);
    }

    public static interface ReleasedirCallback {
        @Delegate
        public int releasedir(java.lang.String path, jnr.ffi.Pointer fi);
    }

    public static interface FsyncdirCallback {
        @Delegate
        public int fsyncdir(java.lang.String path, jnr.ffi.Pointer fi);
    }

    public static interface InitCallback {
        @Delegate
        public jnr.ffi.Pointer init(jnr.ffi.Pointer conn);
    }

    public static interface DestroyCallback {
        @Delegate
        public void destroy(jnr.ffi.Pointer initResult);
    }

    public static interface AccessCallback {
        @Delegate
        public void access(java.lang.String path, int mask);
    }

    public static interface CreateCallback {
        @Delegate
        public void create(java.lang.String path, @jnr.ffi.types.mode_t long mode, jnr.ffi.Pointer fi);
    }

    public static interface FtruncateCallback {
        @Delegate
        public void ftruncate(java.lang.String path, @jnr.ffi.types.off_t long size, jnr.ffi.Pointer fi);
    }

    public static interface FgetattrCallback {
        @Delegate
        public void fgetattr(java.lang.String path, jnr.ffi.Pointer stbuf, jnr.ffi.Pointer fi);
    }

    public static interface LockCallback {
        @Delegate
        public void lock(java.lang.String path, jnr.ffi.Pointer fi, int cmd, jnr.ffi.Pointer flock);
    }

    public static interface UtimensCallback {
        @Delegate
        public void utimens(java.lang.String path, jnr.ffi.Pointer timespec);
    }

    public static interface BmapCallback {
        @Delegate
        public void bmap(java.lang.String path, @jnr.ffi.types.size_t long blocksize, jnr.ffi.Pointer idx);
    }


    public static interface IoctlCallback {
        @Delegate
        public void ioctl(java.lang.String path, int cmd, jnr.ffi.Pointer arg, jnr.ffi.Pointer fi,
                          @jnr.ffi.types.u_int32_t long flags, jnr.ffi.Pointer data);
    }

    public static interface PollCallback {
        @Delegate
        public void poll(java.lang.String path, jnr.ffi.Pointer fi, jnr.ffi.Pointer ph, jnr.ffi.Pointer reventsp);
    }

    public static interface WritebufCallback {
        @Delegate
        public void write_buf(java.lang.String path, jnr.ffi.Pointer buf, @jnr.ffi.types.off_t long off, jnr.ffi.Pointer fi);
    }


    public static interface ReadbufCallback {
        @Delegate
        public void read_buf(java.lang.String path, jnr.ffi.Pointer bufp, @jnr.ffi.types.size_t long size,
                              @jnr.ffi.types.off_t long off, jnr.ffi.Pointer fi);
    }

    public static interface FlockCallback {
        @Delegate
        public void flock(java.lang.String path, jnr.ffi.Pointer fi, int op);
    }

    public static interface FallocateCallback {
        @Delegate
        public void fallocate(java.lang.String path, int mode, @jnr.ffi.types.off_t long off, @jnr.ffi.types.off_t long length, jnr.ffi.Pointer fi);
    }



    public final PublicFunction<GetAttrCallback> getattr = pubFunction(GetAttrCallback.class);
    public final PublicFunction<ReadlinkCallback> readlink = pubFunction(ReadlinkCallback.class);
    @Deprecated
    public final PublicFunction<GetAttrCallback> getdir = pubFunction(GetAttrCallback.class);
    public final PublicFunction<MknodCallback> mknod = pubFunction(MknodCallback.class);
    public final PublicFunction<MkdirCallback> mkdir = pubFunction(MkdirCallback.class);
    public final PublicFunction<UnlinkCallback> unlink = pubFunction(UnlinkCallback.class);
    public final PublicFunction<RmdirCallback> rmdir = pubFunction(RmdirCallback.class);
    public final PublicFunction<SymlinkCallback> symlink = pubFunction(SymlinkCallback.class);

    public final PublicFunction<RenameCallback> rename = pubFunction(RenameCallback.class);
    public final PublicFunction<LinkCallback> link = pubFunction(LinkCallback.class);
    public final PublicFunction<ChmodCallback> chmod = pubFunction(ChmodCallback.class);
    public final PublicFunction<ChownCallback> chown = pubFunction(ChownCallback.class);
    public final PublicFunction<TruncateCallback> truncate = pubFunction(TruncateCallback.class);
    @Deprecated
    public final PublicFunction<GetAttrCallback> utime = pubFunction(GetAttrCallback.class);
    public final PublicFunction<OpenCallback> open = pubFunction(OpenCallback.class);
    public final PublicFunction<ReadCallback> read = pubFunction(ReadCallback.class);
    public final PublicFunction<WriteCallback> write = pubFunction(WriteCallback.class);
    public final PublicFunction<StatfsCallback> statfs = pubFunction(StatfsCallback.class);
    public final PublicFunction<FlushCallback> flush = pubFunction(FlushCallback.class);
    public final PublicFunction<ReleaseCallback> release = pubFunction(ReleaseCallback.class);
    public final PublicFunction<FsyncCallback> fsync = pubFunction(FsyncCallback.class);
    public final PublicFunction<SetxattrCallback> setxattr = pubFunction(SetxattrCallback.class);
    public final PublicFunction<GetxattrCallback> getxattr = pubFunction(GetxattrCallback.class);
    public final PublicFunction<ListxattrCallback> listxattr = pubFunction(ListxattrCallback.class);
    public final PublicFunction<RemovexattrCallback> removexattr = pubFunction(RemovexattrCallback.class);
    public final PublicFunction<OpendirCallback> opendir = pubFunction(OpendirCallback.class);
    public final PublicFunction<ReaddirCallback> readdir = pubFunction(ReaddirCallback.class);
    public final PublicFunction<ReleasedirCallback> releasedir = pubFunction(ReleasedirCallback.class);
    public final PublicFunction<FsyncCallback> fsyncdir = pubFunction(FsyncCallback.class);
    public final PublicFunction<InitCallback> init = pubFunction(InitCallback.class);
    public final PublicFunction<DestroyCallback> destroy = pubFunction(DestroyCallback.class);
    public final PublicFunction<AccessCallback> access = pubFunction(AccessCallback.class);
    public final PublicFunction<CreateCallback> create = pubFunction(CreateCallback.class);
    public final PublicFunction<FtruncateCallback> ftruncate = pubFunction(FtruncateCallback.class);
    public final PublicFunction<FgetattrCallback> fgetattr = pubFunction(FgetattrCallback.class);
    public final PublicFunction<LockCallback> lock = pubFunction(LockCallback.class);
    public final PublicFunction<UtimensCallback> utimens = pubFunction(UtimensCallback.class);
    public final PublicFunction<BmapCallback> bmap = pubFunction(BmapCallback.class);

    public final Padding flag_nullpath_ok = new Padding(NativeType.UINT, 1);
    public final Padding flag_nopath = new Padding(NativeType.UINT, 1);
    public final Padding flag_utime_omit_ok = new Padding(NativeType.UINT, 1);
    public final Padding flag_reserved = new Padding(NativeType.UINT, 29);

    public final PublicFunction<IoctlCallback> ioctl = pubFunction(IoctlCallback.class);
    public final PublicFunction<PollCallback> poll = pubFunction(PollCallback.class);
    public final PublicFunction<WritebufCallback> write_buf = pubFunction(WritebufCallback.class);
    public final PublicFunction<ReadbufCallback> read_buf = pubFunction(ReadbufCallback.class);
    public final PublicFunction<FlockCallback> flock = pubFunction(FlockCallback.class);
    public final PublicFunction<FallocateCallback> fallocate = pubFunction(FallocateCallback.class);
}
