package net.bodz.bas.vfs.impl.loop;

import net.bodz.bas.vfs.FileResolveException;
import net.bodz.bas.vfs.IFile;
import net.bodz.bas.vfs.IFileSystem;
import net.bodz.bas.vfs.VFS;
import net.bodz.bas.vfs.path.BadPathException;
import net.bodz.bas.vfs.path.IPath;
import net.bodz.bas.vfs.path.IPathParser;

/**
 * Loop-Path is path in the format of:
 * 
 * <pre>
 * protocol ':' device-path '!' local-path
 * </pre>
 */
public abstract class LoopPathResolver
        implements IPathParser {

    @Override
    public IPath parse(String path)
            throws BadPathException {
        if (path == null)
            throw new NullPointerException("path");

        int colon = path.indexOf(':');
        if (colon == -1)
            throw new BadPathException("No protocol specified", path);

        String parentLayerPath = path.substring(colon + 1);

        String localPath;
        int bang = parentLayerPath.indexOf('!');
        if (bang == -1)
            localPath = "";
        else {
            localPath = parentLayerPath.substring(bang + 1);
            parentLayerPath = parentLayerPath.substring(0, bang);
        }

        try {
            IFile parentLayer = VFS.resolve(parentLayerPath);
            IFileSystem volume = newVolume(parentLayer);
            return volume.parse(localPath);
        } catch (FileResolveException e) {
            throw new BadPathException("Failed to resolve the device file " + parentLayerPath, e);
        }
    }

    public abstract IFileSystem newVolume(IFile parentLayer);

}
