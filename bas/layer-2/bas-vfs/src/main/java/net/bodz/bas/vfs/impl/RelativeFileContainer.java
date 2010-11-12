package net.bodz.bas.vfs.impl;

import net.bodz.bas.vfs.AbstractVolume;

public class RelativeFileContainer
        extends AbstractVolume {

    private static RelativeFileContainer instance = new RelativeFileContainer();

    public static RelativeFileContainer getInstance() {
        return instance;
    }

}
