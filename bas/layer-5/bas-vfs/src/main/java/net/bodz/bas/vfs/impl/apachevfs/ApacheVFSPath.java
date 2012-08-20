package net.bodz.bas.vfs.impl.apachevfs;

import org.apache.commons.vfs.FileName;

import net.bodz.bas.vfs.path.DefaultPath;

public class ApacheVFSPath
        extends DefaultPath {

    private static final long serialVersionUID = 1L;

    private final FileName fileName;

    public ApacheVFSPath(ApacheFileSystem fileSystem, String uri) {
        super(fileSystem, uri);
        if (fileSystem == null)
            throw new NullPointerException("fileSystem");
        this.fileName = null;
    }

    public ApacheVFSPath(ApacheFileSystem fileSystem, FileName fileName) {
        super(fileSystem, fileName.getURI());
        if (fileSystem == null)
            throw new NullPointerException("fileSystem");
        this.fileName = fileName;
    }

    /**
     * An internal path is constructed by bas.vfs internally, and never used for Apache VFS
     * operations.
     * 
     * @return <code>true</code> If this path is an internal path.
     */
    public boolean isInternalPath() {
        return fileName == null;
    }

    /**
     * @return <code>null</code> if internal path.
     */
    public FileName getFileName() {
        return fileName;
    }

    /**
     * It's impossible to get the parent layer in Apache VFS, though this delegate.
     */
    // public IPath getParentLayer() {
    // return null;
    // }

}
