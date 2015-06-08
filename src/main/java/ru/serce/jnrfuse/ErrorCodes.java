package ru.serce.jnrfuse;

import jnr.constants.platform.Errno;

public class ErrorCodes {
    public static int E2BIG() {
        return Errno.E2BIG.intValue();
    }


    public static int EACCES() {
        return Errno.EACCES.intValue();
    }


    public static int EADDRINUSE() {
        return Errno.EADDRINUSE.intValue();
    }


    public static int EADDRNOTAVAIL() {
        return Errno.EADDRNOTAVAIL.intValue();
    }


    public static int EADV() {
        return 68;
    }


    public static int EAFNOSUPPORT() {
        return Errno.EAFNOSUPPORT.intValue();
    }


    public static int EAGAIN() {
        return Errno.EAGAIN.intValue();
    }


    public static int EALREADY() {
        return 114;
    }


    public static int EBADE() {
        return 52;
    }


    public static int EBADF() {
        return 9;
    }


    public static int EBADFD() {
        return 77;
    }


    public static int EBADMSG() {
        return 74;
    }


    public static int EBADR() {
        return 53;
    }


    public static int EBADRQC() {
        return 56;
    }


    public static int EBADSLT() {
        return 57;
    }


    public static int EBFONT() {
        return 59;
    }


    public static int EBUSY() {
        return 16;
    }


    public static int ECANCELED() {
        return 125;
    }


    public static int ECHILD() {
        return 10;
    }


    public static int ECHRNG() {
        return 44;
    }


    public static int ECOMM() {
        return 70;
    }


    public static int ECONNABORTED() {
        return 103;
    }


    public static int ECONNREFUSED() {
        return 111;
    }


    public static int ECONNRESET() {
        return 104;
    }


    public static int EDEADLK() {
        return 35;
    }


    public static int EDEADLOCK() {
        return EDEADLK();
    }


    public static int EDESTADDRREQ() {
        return 89;
    }


    public static int EDOM() {
        return 33;
    }


    public static int EDOTDOT() {
        return 73;
    }


    public static int EDQUOT() {
        return 122;
    }


    public static int EEXIST() {
        return 17;
    }


    public static int EFAULT() {
        return 14;
    }


    public static int EFBIG() {
        return 27;
    }


    public static int EHOSTDOWN() {
        return 112;
    }


    public static int EHOSTUNREACH() {
        return 113;
    }


    public static int EIDRM() {
        return 43;
    }


    public static int EILSEQ() {
        return 84;
    }


    public static int EINPROGRESS() {
        return 115;
    }


    public static int EINTR() {
        return 4;
    }


    public static int EINVAL() {
        return 22;
    }


    public static int EIO() {
        return 5;
    }


    public static int EISCONN() {
        return 106;
    }


    public static int EISDIR() {
        return 21;
    }


    public static int EISNAM() {
        return 120;
    }


    public static int EKEYEXPIRED() {
        return 127;
    }


    public static int EKEYREJECTED() {
        return 129;
    }


    public static int EKEYREVOKED() {
        return 128;
    }


    public static int EL2HLT() {
        return 51;
    }


    public static int EL2NSYNC() {
        return 45;
    }


    public static int EL3HLT() {
        return 46;
    }


    public static int EL3RST() {
        return 47;
    }


    public static int ELIBACC() {
        return 79;
    }


    public static int ELIBBAD() {
        return 80;
    }


    public static int ELIBEXEC() {
        return 83;
    }


    public static int ELIBMAX() {
        return 82;
    }


    public static int ELIBSCN() {
        return 81;
    }


    public static int ELNRNG() {
        return 48;
    }


    public static int ELOOP() {
        return 40;
    }


    public static int EMEDIUMTYPE() {
        return 124;
    }


    public static int EMFILE() {
        return 24;
    }


    public static int EMLINK() {
        return 31;
    }


    public static int EMSGSIZE() {
        return 90;
    }


    public static int EMULTIHOP() {
        return 72;
    }


    public static int ENAMETOOLONG() {
        return 36;
    }


    public static int ENAVAIL() {
        return 119;
    }


    public static int ENETDOWN() {
        return 100;
    }


    public static int ENETRESET() {
        return 102;
    }


    public static int ENETUNREACH() {
        return 101;
    }


    public static int ENFILE() {
        return 23;
    }


    public static int ENOANO() {
        return 55;
    }


    public static int ENOBUFS() {
        return 105;
    }


    public static int ENOCSI() {
        return 50;
    }


    public static int ENODATA() {
        return 61;
    }


    public static int ENODEV() {
        return 19;
    }


    public static int ENOENT() {
        return 2;
    }


    public static int ENOEXEC() {
        return 8;
    }


    public static int ENOKEY() {
        return 126;
    }


    public static int ENOLCK() {
        return 37;
    }


    public static int ENOLINK() {
        return 67;
    }


    public static int ENOMEDIUM() {
        return 123;
    }


    public static int ENOMEM() {
        return 12;
    }


    public static int ENOMSG() {
        return 42;
    }


    public static int ENONET() {
        return 64;
    }


    public static int ENOPKG() {
        return 65;
    }


    public static int ENOPROTOOPT() {
        return 92;
    }


    public static int ENOSPC() {
        return 28;
    }


    public static int ENOSR() {
        return 63;
    }


    public static int ENOSTR() {
        return 60;
    }


    public static int ENOSYS() {
        return 38;
    }


    public static int ENOTBLK() {
        return 15;
    }


    public static int ENOTCONN() {
        return 107;
    }


    public static int ENOTDIR() {
        return 20;
    }


    public static int ENOTEMPTY() {
        return 39;
    }


    public static int ENOTNAM() {
        return 118;
    }


    public static int ENOTRECOVERABLE() {
        return 131;
    }


    public static int ENOTSOCK() {
        return 88;
    }


    public static int ENOTTY() {
        return 25;
    }


    public static int ENOTUNIQ() {
        return 76;
    }


    public static int ENXIO() {
        return 6;
    }


    public static int EOPNOTSUPP() {
        return 95;
    }


    public static int EOVERFLOW() {
        return 75;
    }


    public static int EOWNERDEAD() {
        return 130;
    }

    /**
     * Operation not permitted
     */
    public static int EPERM() {
        return Errno.EPERM.intValue();
    }


    public static int EPFNOSUPPORT() {
        return 96;
    }


    public static int EPIPE() {
        return 32;
    }


    public static int EPROTO() {
        return 71;
    }


    public static int EPROTONOSUPPORT() {
        return 93;
    }


    public static int EPROTOTYPE() {
        return 91;
    }


    public static int ERANGE() {
        return 34;
    }


    public static int EREMCHG() {
        return 78;
    }


    public static int EREMOTE() {
        return 66;
    }


    public static int EREMOTEIO() {
        return 121;
    }


    public static int ERESTART() {
        return 85;
    }


    public static int EROFS() {
        return 30;
    }


    public static int ESHUTDOWN() {
        return 108;
    }


    public static int ESOCKTNOSUPPORT() {
        return 94;
    }


    public static int ESPIPE() {
        return 29;
    }


    public static int ESRCH() {
        return 3;
    }


    public static int ESRMNT() {
        return 69;
    }


    public static int ESTALE() {
        return 116;
    }


    public static int ESTRPIPE() {
        return 86;
    }


    public static int ETIME() {
        return 62;
    }


    public static int ETIMEDOUT() {
        return 110;
    }


    public static int ETOOMANYREFS() {
        return 109;
    }


    public static int ETXTBSY() {
        return 26;
    }


    public static int EUCLEAN() {
        return 117;
    }


    public static int EUNATCH() {
        return 49;
    }


    public static int EUSERS() {
        return 87;
    }


    public static int EWOULDBLOCK() {
        return EAGAIN();
    }


    public static int EXDEV() {
        return 18;
    }


    public static int EXFULL() {
        return 54;
    }
}
