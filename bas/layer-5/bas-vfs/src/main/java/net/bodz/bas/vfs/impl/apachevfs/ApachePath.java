package net.bodz.bas.vfs.impl.apachevfs;

import org.apache.commons.vfs.FileName;

import net.bodz.bas.vfs.path.IPath;
import net.bodz.bas.vfs.path.PathFormat;
import net.bodz.bas.vfs.path.ProtocolPath;

public class ApachePath
        extends ProtocolPath {

    private static final long serialVersionUID = 1L;

    private final FileName fileName;

    public ApachePath(FileName fileName) {
        super(fileName.getScheme(), fileName.getPath());
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
     * The ApachePath is a root-layered path.
     * 
     * @return <code>null</code>.
     */
    @Override
    public IPath getParentLayer() {
        return null;
    }

    @Override
    public IPath getParent(int n) {
        FileName fileName = this.fileName;
        for (int i = 0; i < n; i++) {
            fileName = fileName.getParent();
            if (fileName == null)
                return null;
        }
        return new ApachePath(fileName);
    }

    @Override
    protected String formatLocal(PathFormat format) {
        // if (format.getEncodeOptions() != 0) ...
        if (format.isDisplaySafe())
            return fileName.getFriendlyURI();
        else
            return fileName.getURI();
    }

}
