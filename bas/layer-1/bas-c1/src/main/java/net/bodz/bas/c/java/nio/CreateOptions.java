package net.bodz.bas.c.java.nio;

import java.nio.file.CopyOption;
import java.nio.file.OpenOption;

public class CreateOptions {

    public static boolean isCreateParents(OpenOption... options) {
        for (OpenOption option : options)
            if (option == CreateOption.CREATE_PARENTS)
                return true;
        return false;
    }

    public static boolean isCreateParents(CopyOption... options) {
        for (CopyOption option : options)
            if (option == CreateOption.CREATE_PARENTS)
                return true;
        return false;
    }

}
