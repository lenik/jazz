package net.bodz.lily.site.test.web;

import net.bodz.bas.site.DefaultSiteDirs;

public class LilyTestDirs
        extends DefaultSiteDirs {

    public LilyTestDirs() {
    }

    static LilyTestDirs instance = new LilyTestDirs();

    public static LilyTestDirs getInstance() {
        return instance;
    }

}
