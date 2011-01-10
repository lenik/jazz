package net.bodz.bas.vfs.impl.javaio;

import net.bodz.bas.vfs.AbstractVolume;
import net.bodz.bas.vfs.path.IPath;
import net.bodz.bas.vfs.path.IllegalPathException;
import net.bodz.bas.vfs.path.PathFormat;

public class JavaioVolume
        extends AbstractVolume {

    @Override
    public IPath getRootPath() {
        return new JavaioPath("/");
    }

    @Override
    public JavaioPath resolve(String localPath)
            throws IllegalPathException {
        return new JavaioPath(localPath);
    }

    @Override
    public JavaioFile resolveFile(String localPath)
            throws IllegalPathException {
        return new JavaioFile(localPath);
    }

    @Override
    public String format(IPath path, PathFormat pathFormat) {
        String localPath = path.getLocalPath();
        return localPath;
    }

    private static final JavaioVolume instance = new JavaioVolume();

    public static JavaioVolume getInstance() {
        return instance;
    }

}
