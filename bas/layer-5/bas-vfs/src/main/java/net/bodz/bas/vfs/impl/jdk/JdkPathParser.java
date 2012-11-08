package net.bodz.bas.vfs.impl.jdk;

import net.bodz.bas.vfs.path.AbstractPathParser;
import net.bodz.bas.vfs.path.BadPathException;
import net.bodz.bas.vfs.path.IPath;

public class JdkPathParser
        extends AbstractPathParser {

    private JdkVfsDevice fileSystem = JdkVfsDevice.getInstance();

    @Override
    public IPath parse(String path)
            throws BadPathException {
        return fileSystem.parse(path);
    }

}
