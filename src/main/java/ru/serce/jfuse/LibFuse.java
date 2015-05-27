package ru.serce.jfuse;

import jnr.ffi.Pointer;
import ru.serce.jfuse.struct.FuseOperations;

public interface LibFuse {
    int fuse_main_real(int argc, String argv[], FuseOperations op, int op_size, Pointer user_data);
}
