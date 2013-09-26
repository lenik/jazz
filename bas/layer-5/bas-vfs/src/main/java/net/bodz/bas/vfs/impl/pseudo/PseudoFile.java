package net.bodz.bas.vfs.impl.pseudo;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.LinkOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileAttributeView;
import java.util.Collections;

import net.bodz.bas.c.java.io.FilePath;
import net.bodz.bas.io.res.IStreamResource;
import net.bodz.bas.vfs.*;
import net.bodz.bas.vfs.inode.Inode;
import net.bodz.bas.vfs.inode.InodeType;

public abstract class PseudoFile
        extends AbstractFile {

    private String localPath;
    protected Inode inode;
    private PseudoFileAttributes attributes;

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
        this.inode = new Inode(null);
        inode.setType(InodeType.blob);
        inode.setData(resource);
    }

    @Override
    public PseudoVfsDevice getDevice() {
        return (PseudoVfsDevice) super.getDevice();
    }

    @Override
    public PseudoPath getPath() {
        PseudoVfsDevice device = getDevice();
        return new PseudoPath(device.getProtocol(), device.getScopeName(), localPath, this);
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
    public <V extends FileAttributeView> V getAttributeView(Class<V> type, LinkOption... options) {
        if (type.isInstance(attributes))
            return type.cast(attributes);
        else
            return null;
    }

    @Override
    public <A extends BasicFileAttributes> A readAttributes(Class<A> type, LinkOption... options)
            throws IOException {
        if (type.isInstance(attributes))
            return type.cast(attributes);
        else
            return null;
    }

    @Override
    public Boolean exists() {
        return true;
    }

    /** ⇱ Implementaton Of {@link IFsBlob}. */
    /* _____________________________ */static section.iface __BLOC__;

    @Override
    protected IStreamResource newResource(Charset charset) {
        return (IStreamResource) inode.getData();
    }

    /** ⇱ Implementaton Of {@link IFsDir}. */
    /* _____________________________ */static section.iface __DIR__;

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
