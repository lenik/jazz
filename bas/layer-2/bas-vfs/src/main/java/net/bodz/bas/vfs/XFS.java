package net.bodz.bas.vfs;

import net.bodz.bas.vfs.path.DefaultPathSystem;
import net.bodz.bas.vfs.path.IPath;
import net.bodz.bas.vfs.path.IPathSystem;
import net.bodz.bas.vfs.path.PathException;

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
