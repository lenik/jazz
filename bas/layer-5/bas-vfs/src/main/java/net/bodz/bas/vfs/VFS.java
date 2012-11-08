package net.bodz.bas.vfs;

import net.bodz.bas.vfs.path.BadPathException;
import net.bodz.bas.vfs.path.IPath;
import net.bodz.bas.vfs.path.IPathSystem;
import net.bodz.bas.vfs.path.PathSystem;

public class VFS {

    private static IPathSystem pathSystem;

    static {
        pathSystem = new PathSystem();
    }

    public static IPathSystem getPathSystem() {
        return pathSystem;
    }

    public static IPath parse(String path)
            throws BadPathException {
        return pathSystem.parse(path);
    }

    public static IFile resolve(String path)
            throws FileResolveException {
        IPath p = parse(path);
        return p.resolve();
    }

}
