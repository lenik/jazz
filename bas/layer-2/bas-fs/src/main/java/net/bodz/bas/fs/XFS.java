package net.bodz.bas.fs;

import net.bodz.bas.fs.path.DefaultPathSystem;
import net.bodz.bas.fs.path.IPath;
import net.bodz.bas.fs.path.IPathSystem;
import net.bodz.bas.fs.path.PathException;

public class XFS {

    private static IPathSystem pathSystem;

    static {
        pathSystem = new DefaultPathSystem();
    }

    public static IPathSystem getPathSystem() {
        return pathSystem;
    }

    public static IPath resolve(String path)
            throws PathException {
        return pathSystem.resolve(path);
    }

    public static IFile resolveFile(String path)
            throws PathException {
        IPath p = resolve(path);
        return p.toFile();
    }

}
