package net.bodz.bas.vfs.impl.jdk;

import net.bodz.bas.vfs.AbstractVfsDriver;
import net.bodz.bas.vfs.path.IPathSystem;

public class JdkVfsDriver
        extends AbstractVfsDriver {

    @Override
    public void configure(IPathSystem pathSystem) {
        JdkPathParser parser = new JdkPathParser();
        pathSystem.addPathParser("file", parser);
    }

    private static final JdkVfsDriver instance = new JdkVfsDriver();

    public static JdkVfsDriver getInstance() {
        return instance;
    }

}
