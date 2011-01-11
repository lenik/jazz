package net.bodz.bas.vfs.impl.javaio;

import net.bodz.bas.vfs.path.AbstractPathResolver;
import net.bodz.bas.vfs.path.BadPathException;
import net.bodz.bas.vfs.path.IPath;

/**
 * javaio:/foo/bar
 */
public class JavaioPathResolver
        extends AbstractPathResolver {

    private JavaioVolume volume = JavaioVolume.getInstance();

    @Override
    public boolean accepts(String protocol) {
        return "javaio".equals(protocol);
    }

    @Override
    public IPath resolve(String path)
            throws BadPathException {

        if (!path.startsWith("javaio:"))
            throw new BadPathException("Not a Javaio-Path", path);

        path = path.substring(7);
        return volume.resolve(path);
    }

}
