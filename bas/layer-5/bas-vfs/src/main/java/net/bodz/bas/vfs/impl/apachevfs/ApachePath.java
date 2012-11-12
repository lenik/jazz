package net.bodz.bas.vfs.impl.apachevfs;

import org.apache.commons.vfs.FileName;

import net.bodz.bas.vfs.path.DefaultPath;

public class ApachePath
        extends DefaultPath {

    private static final long serialVersionUID = 1L;

    private final FileName fileName;

    public ApachePath(ApacheVfsDevice device, String uri) {
        super(device, uri);
        this.fileName = null;
    }

    public ApachePath(ApacheVfsDevice device, FileName fileName) {
        super(device, fileName.getURI());
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
