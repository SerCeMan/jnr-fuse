package ru.serce.jnrfuse.struct;

import jnr.ffi.NativeType;
import jnr.ffi.Runtime;
import jnr.ffi.BaseStruct;

import static ru.serce.jnrfuse.FuseCallbacks.*;

/**
 * fuse_operations struct
 *
 * @author Sergey Tselovalnikov
 * @since 30.05.15
 */
public class FuseOperations extends BaseStruct {
    public final Func<GetAttrCallback> getattr = func(GetAttrCallback.class);
    public final Func<ReadlinkCallback> readlink = func(ReadlinkCallback.class);
    @Deprecated
    public final Func<GetAttrCallback> getdir = func(GetAttrCallback.class);
    public final Func<MknodCallback> mknod = func(MknodCallback.class);
    public final Func<MkdirCallback> mkdir = func(MkdirCallback.class);
    public final Func<UnlinkCallback> unlink = func(UnlinkCallback.class);
    public final Func<RmdirCallback> rmdir = func(RmdirCallback.class);
    public final Func<SymlinkCallback> symlink = func(SymlinkCallback.class);

    public final Func<RenameCallback> rename = func(RenameCallback.class);
    public final Func<LinkCallback> link = func(LinkCallback.class);
    public final Func<ChmodCallback> chmod = func(ChmodCallback.class);
    public final Func<ChownCallback> chown = func(ChownCallback.class);
    public final Func<TruncateCallback> truncate = func(TruncateCallback.class);
    @Deprecated
    public final Func<GetAttrCallback> utime = func(GetAttrCallback.class);
    public final Func<OpenCallback> open = func(OpenCallback.class);
    public final Func<ReadCallback> read = func(ReadCallback.class);
    public final Func<WriteCallback> write = func(WriteCallback.class);
    public final Func<StatfsCallback> statfs = func(StatfsCallback.class);
    public final Func<FlushCallback> flush = func(FlushCallback.class);
    public final Func<ReleaseCallback> release = func(ReleaseCallback.class);
    public final Func<FsyncCallback> fsync = func(FsyncCallback.class);
    public final Func<SetxattrCallback> setxattr = func(SetxattrCallback.class);
    public final Func<GetxattrCallback> getxattr = func(GetxattrCallback.class);
    public final Func<ListxattrCallback> listxattr = func(ListxattrCallback.class);
    public final Func<RemovexattrCallback> removexattr = func(RemovexattrCallback.class);
    public final Func<OpendirCallback> opendir = func(OpendirCallback.class);
    public final Func<ReaddirCallback> readdir = func(ReaddirCallback.class);
    public final Func<ReleasedirCallback> releasedir = func(ReleasedirCallback.class);
    public final Func<FsyncdirCallback> fsyncdir = func(FsyncdirCallback.class);
    public final Func<InitCallback> init = func(InitCallback.class);
    public final Func<DestroyCallback> destroy = func(DestroyCallback.class);
    public final Func<AccessCallback> access = func(AccessCallback.class);
    public final Func<CreateCallback> create = func(CreateCallback.class);
    public final Func<FtruncateCallback> ftruncate = func(FtruncateCallback.class);
    public final Func<FgetattrCallback> fgetattr = func(FgetattrCallback.class);
    public final Func<LockCallback> lock = func(LockCallback.class);
    public final Func<UtimensCallback> utimens = func(UtimensCallback.class);
    public final Func<BmapCallback> bmap = func(BmapCallback.class);
    /**
     * Flag indicating that the filesystem can accept a NULL path
     * as the first argument for the following operations:
     *
     * read, write, flush, release, fsync, readdir, releasedir,
     * fsyncdir, ftruncate, fgetattr, lock, ioctl and poll
     *
     * If this flag is set these operations continue to work on
     * unlinked files even if "-ohard_remove" option was specified.
     */
    public final Padding flag_nullpath_ok = new Padding(NativeType.UCHAR, 1);
    /**
     * Flag indicating that the path need not be calculated for
     * the following operations:
     *
     * read, write, flush, release, fsync, readdir, releasedir,
     * fsyncdir, ftruncate, fgetattr, lock, ioctl and poll
     *
     * Closely related to flag_nullpath_ok, but if this flag is
     * set then the path will not be calculaged even if the file
     * wasn't unlinked.  However the path can still be non-NULL if
     * it needs to be calculated for some other reason.
     */
    public final Padding flag_nopath = new Padding(NativeType.UCHAR, 1);
    /**
     * Flag indicating that the filesystem accepts special
     * UTIME_NOW and UTIME_OMIT values in its utimens operation.
     */
    public final Padding flag_utime_omit_ok = new Padding(NativeType.UCHAR, 1);
    /**
     * Reserved flags, don't set
     */
    public final Padding flag_reserved = new Padding(NativeType.UCHAR, 1);
    public final Func<IoctlCallback> ioctl = func(IoctlCallback.class);
    public final Func<PollCallback> poll = func(PollCallback.class);
    public final Func<WritebufCallback> write_buf = func(WritebufCallback.class);
    public final Func<ReadbufCallback> read_buf = func(ReadbufCallback.class);
    public final Func<FlockCallback> flock = func(FlockCallback.class);
    public final Func<FallocateCallback> fallocate = func(FallocateCallback.class);

    public FuseOperations(Runtime runtime) {
        super(runtime);
    }
}
