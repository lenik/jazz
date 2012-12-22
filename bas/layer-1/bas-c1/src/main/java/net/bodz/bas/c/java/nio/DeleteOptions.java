package net.bodz.bas.c.java.nio;

public class DeleteOptions {

    public static boolean isDeleteTree(DeleteOption... options) {
        for (DeleteOption option : options)
            if (option == TreeDeleteOption.DELETE_TREE)
                return true;
        return false;
    }

    public static boolean isRemoveEmptyParents(DeleteOption... options) {
        for (DeleteOption option : options)
            if (option == TreeDeleteOption.REMOVE_EMPTY_PARENTS)
                return true;
        return false;
    }

}
