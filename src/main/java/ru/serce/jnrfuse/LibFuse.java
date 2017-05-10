package ru.serce.jnrfuse;

import jnr.ffi.Pointer;
import jnr.ffi.types.size_t;
import jnr.ffi.types.ssize_t;
import ru.serce.jnrfuse.struct.FuseBufvec;
import ru.serce.jnrfuse.struct.FuseContext;
import ru.serce.jnrfuse.struct.FuseOperations;
import ru.serce.jnrfuse.struct.FusePollhandle;

/**
 * Library interface of FUSE
 *
 * @author Sergey Tselovalnikov
 * @since 03.06.15
 */
public interface LibFuse {

    @size_t
    long fuse_buf_size(FuseBufvec bufv);

    @ssize_t
    long fuse_buf_copy(FuseBufvec dstv, FuseBufvec srcv, int flags);

    // poll
    void fuse_pollhandle_destroy(FusePollhandle ph);

    int fuse_notify_poll(FusePollhandle ph);

    FuseContext fuse_get_context();

    /**
     * Main function of FUSE.
     * <p>
     * This function does the following:
     * - parses command line options (-d -s and -h)
     * - passes relevant mount options to the fuse_mount()
     * - installs signal handlers for INT, HUP, TERM and PIPE
     * - registers an exit handler to unmount the filesystem on program exit
     * - creates a fuse handle
     * - registers the operations
     * - calls either the single-threaded or the multi-threaded event loop
     *
     * @param argc      the argument counter passed to the main() function
     * @param argv      the argument vector passed to the main() function
     * @param op        the file system operation
     * @param user_data user data supplied in the context during the init() method
     * @return 0 on success, nonzero on failure
     */
    int fuse_main_real(int argc, String[] argv, FuseOperations op, int op_size, Pointer user_data);
}
