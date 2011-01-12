package net.bodz.bas.vfs.impl.win32.apachevfs;

import net.bodz.bas.vfs.path.AbstractPathResolver;
import net.bodz.bas.vfs.path.BadPathException;
import net.bodz.bas.vfs.path.IPath;

import org.apache.commons.vfs.FileName;
import org.apache.commons.vfs.FileSystemException;
import org.apache.commons.vfs.VFS;

public class ApacheVFSPathResolver
        extends AbstractPathResolver {

    @Override
    public boolean accepts(String protocol) {
        if (protocol == null || protocol.isEmpty())
            return false;

        String[] schemes;
        try {
            schemes = VFS.getManager().getSchemes();
        } catch (FileSystemException e) {
            return false;
        }

        for (int i = 0; i < schemes.length; i++)
            if (protocol.equals(schemes))
                return true;
        return false;
    }

    @Override
    public IPath resolve(String path)
            throws BadPathException {
        try {
            FileName fileName = VFS.getManager().resolveURI(path);
            
        } catch (FileSystemException e) {
            throw new BadPathException("Failed to resolveURI", e);
        }
        return null;
    }

}
