package net.bodz.bas.vfs.impl.jdk;

import net.bodz.bas.vfs.AbstractVfsDriver;
import net.bodz.bas.vfs.path.IGenericPathParser;
import net.bodz.bas.vfs.path.IPathSystem;

public class URLVfsDriver
        extends AbstractVfsDriver {

    @Override
    public void configure(IPathSystem pathSystem) {
        URLPathParser parser = new URLPathParser();
        pathSystem.addGenericPathParser(parser, IGenericPathParser.NORMAL_PRIORITY);
    }

    private static URLVfsDriver instance = new URLVfsDriver();

    public static URLVfsDriver getInstance() {
        return instance;
    }

    public static void setInstance(URLVfsDriver instance) {
        URLVfsDriver.instance = instance;
    }

}
