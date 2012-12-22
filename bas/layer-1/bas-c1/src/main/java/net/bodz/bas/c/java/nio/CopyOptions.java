package net.bodz.bas.c.java.nio;

import java.nio.file.CopyOption;
import java.nio.file.StandardCopyOption;

public class CopyOptions {

    public static boolean isNoReplaceExisting(CopyOption... options) {
        return !isReplaceExisting(options);
    }

    public static boolean isReplaceExisting(CopyOption... options) {
        for (CopyOption option : options)
            if (option == StandardCopyOption.REPLACE_EXISTING)
                return true;
        return false;
    }

    public static boolean isNoCopyAttributes(CopyOption... options) {
        return !isCopyAttributes(options);
    }

    public static boolean isCopyAttributes(CopyOption... options) {
        for (CopyOption option : options)
            if (option == StandardCopyOption.COPY_ATTRIBUTES)
                return true;
        return false;
    }

    public static boolean isNoAtomicMove(CopyOption... options) {
        return !isAtomicMove(options);
    }

    public static boolean isAtomicMove(CopyOption... options) {
        for (CopyOption option : options)
            if (option == StandardCopyOption.ATOMIC_MOVE)
                return true;
        return false;
    }

}
