package net.bodz.violet.server;

import net.bodz.bas.site.DefaultSiteDirs;

public class VioletDirs
        extends DefaultSiteDirs {

    public VioletDirs() {
    }

    static VioletDirs instance = new VioletDirs();

    public static VioletDirs getInstance() {
        return instance;
    }

}
