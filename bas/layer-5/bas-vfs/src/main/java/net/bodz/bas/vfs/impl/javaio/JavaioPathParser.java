package net.bodz.bas.vfs.impl.javaio;

import net.bodz.bas.vfs.path.AbstractPathParser;
import net.bodz.bas.vfs.path.BadPathException;
import net.bodz.bas.vfs.path.IPath;

public class JavaioPathParser
        extends AbstractPathParser {

    private JavaioFileSystem fileSystem = JavaioFileSystem.getInstance();

    @Override
    public IPath parse(String path)
            throws BadPathException {
        return fileSystem.parse(path);
    }

}
