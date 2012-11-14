package net.bodz.bas.vfs;

import net.bodz.bas.vfs.path.BadPathException;
import net.bodz.bas.vfs.path.IPath;

public class VFS {

    private static IFileSystem fileSystem;

    static {
        fileSystem = new FileSystem();
    }

    public static IFileSystem getFileSystem() {
        return fileSystem;
    }

    public static IVfsDriver getDriver(String protocol) {
        return fileSystem.getDriver(protocol);
    }

    public static IPath parse(String path)
            throws BadPathException {
        return fileSystem.parse(path);
    }

    public static IFile resolve(String path)
            throws BadPathException, FileResolveException {
        IPath p = parse(path);
        return fileSystem.resolve(p);
    }

    public static IFile resolve(IPath path)
            throws FileResolveException {
        return fileSystem.resolve(path);
    }

}
