package net.bodz.bas.vfs.impl.pseudo;

import java.nio.charset.Charset;
import java.nio.file.Path;

import net.bodz.bas.c.java.io.FilePath;
import net.bodz.bas.io.res.IRandomResource;
import net.bodz.bas.meta.decl.NotNull;
import net.bodz.bas.vfs.IFsBlob;
import net.bodz.bas.vfs.MutableFile;
import net.bodz.bas.vfs.PathUnsupportedException;

public abstract class PseudoFile
        extends MutableFile {

    protected final String localPath;
    protected final IRandomResource resource;

    /**
     * @throws NullPointerException
     *             If <code>localPath</code> is <code>null</code>.
     */
    public PseudoFile(String localPath, IRandomResource resource) {
        this(PseudoVfsDriver.getInstance().getDefaultDevice(), localPath, resource);
    }

    /**
     * @throws NullPointerException
     *             If <code>localPath</code> is <code>null</code>.
     */
    public PseudoFile(PseudoVfsDevice device, String localPath, IRandomResource resource) {
        super(device, FilePath.baseName(localPath), null);

        if (resource == null)
            throw new NullPointerException("resource");

        this.localPath = localPath;
        this.resource = resource;
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

    @NotNull
    @Override
    public Path toPath()
            throws PathUnsupportedException {
        throw new PathUnsupportedException();
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

    /** ⇱ Implementaton Of {@link IFsBlob}. */
    /* _____________________________ */static section.iface __BLOC__;

    @Override
    public IRandomResource getResource(Charset charset) {
        // XXX resource.setCharset(charset);
        return resource;
    }

}
