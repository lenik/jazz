package net.bodz.bas.vfs.impl.pseudo;

import java.nio.charset.Charset;
import java.util.Collections;

import net.bodz.bas.c.java.io.FilePath;
import net.bodz.bas.io.resource.IStreamResource;
import net.bodz.bas.vfs.AbstractFile;
import net.bodz.bas.vfs.IFile;
import net.bodz.bas.vfs.VFSException;
import net.bodz.bas.vfs.path.IPath;
import net.bodz.bas.vfs.util.IFileFilter;
import net.bodz.bas.vfs.util.IFilenameFilter;

public abstract class PseudoFile
        extends AbstractFile {

    private static final long serialVersionUID = 1L;

    private String localPath;
    private IStreamResource resource;
    private long creationTime;
    private long lastModifiedTime;

    /**
     * @throws NullPointerException
     *             If <code>localPath</code> is <code>null</code>.
     */
    public PseudoFile(String localPath, IStreamResource resource) {
        this(PseudoVfsDriver.getInstance().getDefaultDevice(), localPath, resource);
    }

    /**
     * @throws NullPointerException
     *             If <code>localPath</code> is <code>null</code>.
     */
    public PseudoFile(PseudoVfsDevice device, String localPath, IStreamResource resource) {
        super(device, FilePath.getBaseName(localPath));

        if (resource == null)
            throw new NullPointerException("resource");

        this.localPath = localPath;
        this.resource = resource;

        creationTime = lastModifiedTime = System.currentTimeMillis();
    }

    @Override
    public IFile clone() {
        return (IFile) nativeClone();
    }

    @Override
    protected PseudoFile populate(Object obj) {
        super.populate(obj);
        if (obj instanceof PseudoFile) {
            PseudoFile o = (PseudoFile) obj;
            this.creationTime = o.creationTime;
            this.lastModifiedTime = o.lastModifiedTime;
        }
        return this;
    }

    @Override
    public IPath getPath() {
        return new PseudoPath(getDevice(), localPath, this);
    }

    /**
     * You can rename a detached pseudo file directly.
     * 
     * @param baseName
     *            The new base name.
     */
    @Override
    public void setName(String baseName) {
        super.setName(baseName);
    }

    @Override
    public Boolean exists() {
        return true;
    }

    @Override
    public boolean isBlob() {
        return true;
    }

    @Override
    public Long getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(long creationTime) {
        this.creationTime = creationTime;
    }

    @Override
    public Long getLastModifiedTime() {
        return lastModifiedTime;
    }

    @Override
    public boolean setLastModifiedTime(long date) {
        this.lastModifiedTime = date;
        return true;
    }

// -o IFsBlob

    @Override
    public IStreamResource getResource(Charset charset) {
        return resource;
    }

    // -o IFsTree

    @Override
    public IFile getChild(String childName) {
        return null;
    }

    @Override
    public Iterable<? extends IFile> children(IFilenameFilter nameFilter)
            throws VFSException {
        return Collections.emptyList();
    }

    @Override
    public Iterable<? extends IFile> children(IFileFilter nameFilter)
            throws VFSException {
        return Collections.emptyList();
    }

}
