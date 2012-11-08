package net.bodz.bas.vfs;

import static net.bodz.bas.vfs.FileModifier.*;

import java.io.IOException;
import java.io.Serializable;
import java.nio.charset.Charset;

import net.bodz.bas.err.UnexpectedException;
import net.bodz.bas.i18n.LocaleColos;
import net.bodz.bas.io.resource.IStreamInputSource;
import net.bodz.bas.io.resource.IStreamOutputTarget;
import net.bodz.bas.io.resource.IStreamResource;
import net.bodz.bas.sugar.Tooling;
import net.bodz.bas.vfs.path.BadPathException;
import net.bodz.bas.vfs.path.IPath;
import net.bodz.bas.vfs.tools.HeuristicProbing;
import net.bodz.bas.vfs.tools.IProbing;
import net.bodz.bas.vfs.tools.LazyProbing;
import net.bodz.bas.vfs.util.IFilenameFilter;

public abstract class AbstractFile
        extends AbstractFsEntry
        implements IFile, Serializable {

    private static final long serialVersionUID = 1L;

    private Charset preferredCharset = Charset.defaultCharset();

    /**
     * @param device
     *            Non-<code>null</code> device object which this file residues on.
     * @param path
     *            Non-<code>null</code> path.
     */
    public AbstractFile(IVfsDevice device, String baseName) {
        super(device, baseName);
    }

    @Override
    protected AbstractFile populate(Object obj) {
        super.populate(obj);
        if (obj instanceof AbstractFile) {
            AbstractFile o = (AbstractFile) obj;
            this.preferredCharset = o.preferredCharset;
        }
        return this;
    }

    @Override
    public IFile getParentFile() {
        IPath parentPath = getPath().getParent();
        if (parentPath == null)
            return null;
        try {
            return parentPath.resolve();
        } catch (FileResolveException e) {
            throw new UnexpectedException(e.getMessage(), e);
        }
    }

    @Override
    public Charset getPreferredCharset() {
        return preferredCharset;
    }

    @Override
    public void setPreferredCharset(Charset charset) {
        if (charset == null)
            throw new NullPointerException("charset");
        this.preferredCharset = charset;
    }

    @Override
    public final void setPreferredCharset(String charsetName) {
        if (charsetName == null)
            throw new NullPointerException("charsetName");
        setPreferredCharset(Charset.forName(charsetName));
    }

    @Override
    public boolean isHidden() {
        return false;
    }

    @Override
    public boolean isExecutable() {
        return false;
    }

    @Override
    public Long getLength() {
        return null;
    }

    @Override
    public final long length() {
        Long len = getLength();
        if (len == null)
            return 0;
        else
            return len.longValue();
    }

    @Override
    public boolean isSeekable() {
        return false;
    }

    @Override
    public int getModifiers(int mask) {
        int bits = 0;

        if ((mask & MASK_ACCESSIBLE) != 0) {
            if (this.isReadable())
                bits |= READABLE;
            if (this.isWritable())
                bits |= WRITABLE;
            if (this.isExecutable())
                bits |= EXECUTABLE;
            if (this.isSeekable())
                bits |= SEEKABLE;
        }

        if (0 != (mask & MASK_ATTRIB)) {
            if (0 != (mask & HIDDEN))
                if (this.isHidden())
                    bits |= HIDDEN;
        }

        if (0 != (mask & MASK_EXISTENCE)) {
            Boolean exists = this.exists();
            if (exists == Boolean.TRUE)
                bits |= EXIST;
            if (exists == Boolean.FALSE)
                bits |= NEXIST;
        }

        if (0 != (mask & MASK_LENGTHY)) {
            Long length = this.getLength();
            if (length != null) {
                if (length == 0)
                    bits |= EMPTY;
                else
                    bits |= NEMPTY;
            }
        }

        if (0 != (mask & MASK_OBJTYPE))
            if (isTree())
                bits |= DIRECTORY;
            else
                bits |= FILE;

        if (0 != (mask & CNTTYPE) && (bits & EXIST) != 0) {
            IProbing probe;
            try {
                probe = tooling()._for(HeuristicProbing.class);
                if (probe.isText())
                    bits |= TEXT;
                else
                    bits |= BINARY;
            } catch (IOException e) {
                probe = tooling()._for(LazyProbing.class);
                try {
                    if (probe.isText())
                        bits |= TEXT;
                    else
                        bits |= BINARY;
                } catch (IOException e1) {
                }
            }
        }
        return bits;
    }

    @Override
    public boolean renameTo(String destLocalPath)
            throws BadPathException {
        String fromLocalPath = getPath().getLocalPath();
        try {
            getDevice().rename(fromLocalPath, destLocalPath);
            return true;
        } catch (VFSException e) {
            return false;
        }
    }

    // -o IFsBlob

    /**
     * @return <code>null</code> If no resource available for this fs-entry.
     */
    @Override
    public final IStreamResource getResource() {
        Charset charset = getPreferredCharset();
        if (charset == null)
            charset = LocaleColos.charset.get();
        return getResource(charset);
    }

    /**
     * @return <code>null</code> If no resource available for this fs-entry.
     */
    @Override
    public final IStreamResource getResource(String charsetName) {
        Charset charset = Charset.forName(charsetName);
        return getResource(charset);
    }

    /**
     * @return <code>null</code> If no resource available for this fs-entry.
     */
    @Override
    public abstract IStreamResource getResource(Charset charset);

    @Override
    public final IStreamInputSource getInputSource() {
        Charset charset = getPreferredCharset();
        if (charset == null)
            charset = LocaleColos.charset.get();
        return getInputSource(charset);
    }

    @Override
    public final IStreamInputSource getInputSource(String charsetName) {
        Charset charset = Charset.forName(charsetName);
        return getInputSource(charset);
    }

    @Override
    public IStreamInputSource getInputSource(Charset charset) {
        IStreamResource resource = getResource(charset);
        return resource;
    }

    @Override
    public final IStreamOutputTarget getOutputTarget() {
        // There no preferred-append-mode.
        return getOutputTarget(false);
    }

    @Override
    public final IStreamOutputTarget getOutputTarget(String charsetName) {
        return getOutputTarget(false, charsetName);
    }

    @Override
    public final IStreamOutputTarget getOutputTarget(Charset charset) {
        return getOutputTarget(false, charset);
    }

    @Override
    public final IStreamOutputTarget getOutputTarget(boolean appendMode) {
        Charset charset = getPreferredCharset();
        if (charset == null)
            charset = LocaleColos.charset.get();
        // There no preferred-append-mode.
        return getOutputTarget(appendMode, charset);
    }

    @Override
    public final IStreamOutputTarget getOutputTarget(boolean appendMode, String charsetName) {
        Charset charset = Charset.forName(charsetName);
        return getOutputTarget(appendMode, charset);
    }

    @Override
    public IStreamOutputTarget getOutputTarget(boolean appendMode, Charset charset) {
        if (isAutoCreateParents()) {
            IFile parent = getParentFile();
            if (parent != null)
                parent.createTree();
        }
        IStreamResource resource = getResource(charset);
        resource.setAppendMode(appendMode);
        return resource;
    }

    // -o IFsTree

    @Override
    public boolean createTree() {
        return false;
    }

    @Override
    public boolean isIterable() {
        return isTree();
    }

    @Override
    public IFile resolve(String spec)
            throws BadPathException, FileResolveException {
        return getPath().join(spec).resolve();
    }

    @Override
    public Iterable<? extends IFile> children()
            throws VFSException {
        return children(IFilenameFilter.TRUE);
    }

    // -o IToolable

    @Override
    public Tooling tooling() {
        return new Tooling(this);
    }

    @Override
    public int hashCode() {
        int hash = super.hashCode();
        assert preferredCharset != null;
        hash += preferredCharset.hashCode();
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof AbstractFile))
            return false;
        AbstractFile o = (AbstractFile) obj;
        if (!preferredCharset.equals(o.preferredCharset))
            return false;
        return super.equals(o);
    }

}
