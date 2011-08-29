package net.bodz.bas.vfs.impl.apachevfs;

import net.bodz.bas.vfs.IVolume;
import net.bodz.bas.vfs.path.AbstractPath;

import org.apache.commons.vfs.FileName;

public class ApacheVFSPath
        extends AbstractPath {

    private static final long serialVersionUID = 1L;

    private final ApacheVFSVolume volume;
    private final FileName fileName;

    public ApacheVFSPath(ApacheVFSVolume volume, String uri) {
        super(uri);
        if (volume == null)
            throw new NullPointerException("volume");
        this.volume = volume;
        this.fileName = null;
    }

    public ApacheVFSPath(ApacheVFSVolume volume, FileName fileName) {
        super(fileName.getURI());
        if (volume == null)
            throw new NullPointerException("volume");
        this.volume = volume;
        this.fileName = fileName;
    }

    @Override
    public IVolume getVolume() {
        return volume;
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
