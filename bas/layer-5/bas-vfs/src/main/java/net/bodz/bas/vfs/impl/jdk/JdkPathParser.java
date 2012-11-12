package net.bodz.bas.vfs.impl.jdk;

import net.bodz.bas.vfs.path.AbstractPathParser;
import net.bodz.bas.vfs.path.BadPathException;

public class JdkPathParser
        extends AbstractPathParser {

    private JdkVfsDevice device = JdkVfsDevice.getInstance();

    @Override
    public JdkPath parse(String path)
            throws BadPathException {
        return device.parse(path);
    }

}
