package net.bodz.bas.vfs.impl.javafile;

import net.bodz.bas.vfs.path.IPath;
import net.bodz.bas.vfs.path.ITransientPathResolver;
import net.bodz.bas.vfs.path.PathException;

public class FilePathResolver
        implements ITransientPathResolver {

    @Override
    public boolean accepts(String protocol) {
        return "file".equals(protocol);
    }

    @Override
    public IPath resolve(String path)
            throws PathException {
        if (path.startsWith("file://"))
            path = path.substring(7);
        return new FilePath(path);
    }

}
