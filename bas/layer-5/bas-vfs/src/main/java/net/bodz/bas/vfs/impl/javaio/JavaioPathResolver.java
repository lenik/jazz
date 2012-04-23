package net.bodz.bas.vfs.impl.javaio;

import net.bodz.bas.vfs.path.AbstractPathResolver;
import net.bodz.bas.vfs.path.BadPathException;
import net.bodz.bas.vfs.path.IPath;

public class JavaioPathResolver
        extends AbstractPathResolver {

    private JavaioVolume volume = JavaioVolume.getInstance();

    @Override
    public IPath resolve(String path)
            throws BadPathException {
        return volume.resolve(path);
    }

}
