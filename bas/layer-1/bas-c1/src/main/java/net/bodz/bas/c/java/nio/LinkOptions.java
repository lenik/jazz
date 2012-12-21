package net.bodz.bas.c.java.nio;

import java.nio.file.LinkOption;

public class LinkOptions {

    public static boolean isFollowLinks(LinkOption... options) {
        for (LinkOption option : options)
            if (option == LinkOption.NOFOLLOW_LINKS)
                return false;
        return true;
    }

}
