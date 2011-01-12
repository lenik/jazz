package net.bodz.bas.vfs;

import net.bodz.bas.vfs.path.BadPathException;
import net.bodz.bas.vfs.path.DefaultPathSystem;
import net.bodz.bas.vfs.path.IPath;
import net.bodz.bas.vfs.path.IPathSystem;

public class VFS {

    private static IPathSystem pathSystem;

    static {
        pathSystem = new DefaultPathSystem();
    }

    public static IPathSystem getPathSystem() {
        return pathSystem;
    }

    public static IPath resolve(String path)
            throws BadPathException {
        return pathSystem.resolve(path);
    }

    public static IFile resolveFile(String path)
            throws PathResolveException {
        IPath p = resolve(path);
        return p.toFile();
    }

}
