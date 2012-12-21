package net.bodz.bas.c.java.nio;

import java.nio.file.OpenOption;
import java.nio.file.StandardOpenOption;

public class OpenOptions {

    public static boolean isAppend(OpenOption... options) {
        for (OpenOption option : options)
            if (option == StandardOpenOption.APPEND)
                return true;
        return false;
    }

    public static boolean isCreateNew(OpenOption... options) {
        for (OpenOption option : options)
            if (option == StandardOpenOption.CREATE_NEW)
                return true;
        return false;
    }

}
