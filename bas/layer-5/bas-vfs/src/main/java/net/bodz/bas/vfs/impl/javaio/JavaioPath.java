package net.bodz.bas.vfs.impl.javaio;

import net.bodz.bas.vfs.IVolume;
import net.bodz.bas.vfs.path.AbstractPath;
import net.bodz.bas.vfs.path.IPath;

public class JavaioPath
        extends AbstractPath {

    private static final long serialVersionUID = 1L;

    private static final JavaioVolume volume = JavaioVolume.getInstance();

    public JavaioPath(String localPath) {
        super(localPath);
    }

    @Override
    public IVolume getVolume() {
        return volume;
    }

    @Override
    public IPath getParentLayer() {
        return null;
    }

}
