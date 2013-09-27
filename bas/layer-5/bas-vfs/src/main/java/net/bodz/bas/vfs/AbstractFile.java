package net.bodz.bas.vfs;

import static net.bodz.bas.vfs.FileFlags.*;

import java.io.IOException;
import java.nio.charset.Charset;

import net.bodz.bas.c.java.nio.CommonOpenConfig;
import net.bodz.bas.err.UnexpectedException;
import net.bodz.bas.i18n.LocaleColos;
import net.bodz.bas.io.res.IOpenResourceListener;
import net.bodz.bas.io.res.IStreamInputSource;
import net.bodz.bas.io.res.IStreamOutputTarget;
import net.bodz.bas.io.res.IStreamResource;
import net.bodz.bas.io.res.IStreamResourceWrapper;
import net.bodz.bas.io.res.OpenResourceEvent;
import net.bodz.bas.sugar.Tooling;
import net.bodz.bas.vfs.path.BadPathException;
import net.bodz.bas.vfs.path.IPath;
import net.bodz.bas.vfs.util.content.HeuristicProbing;
import net.bodz.bas.vfs.util.content.IProbing;
import net.bodz.bas.vfs.util.content.LazyProbing;

public abstract class AbstractFile
        extends FsObject
        implements IFile/* , Serializable */{

    private Charset preferredCharset = Charset.defaultCharset();

    private transient int flags;
    private transient int knownMask;

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
    public <T> T to(Class<T> clazz) {
        return new Tooling(this).to(clazz);
    }

    /** ⇱ Implementation Of {@link IFile}. */
    /* _____________________________ */static section.iface __FILE__;

    @Override
    public IFile resolve(String spec)
            throws BadPathException, FileResolveException {
        IPath joinedPath = getPath().join(spec);
        IFile file = VFS.resolve(joinedPath);
        return file;
    }

    /** ⇱ Implementation Of {@link IFsObject}. */
    /* _____________________________ */static section.iface __FS_OBJECT__;

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
    public int getFlags(int mask) {
        int unknownMask = (mask & ~knownMask);
        if (unknownMask != 0) {
            int unknownFlags = retrieveFlags(unknownMask);
            flags = (flags & ~unknownMask) | unknownFlags;
        }
        return flags & mask;
    }

    protected int retrieveFlags(int mask) {
        int bits = 0;

        IFileAttributes attrs = getAttributes();

        if ((mask & MASK_ACCESSIBLE) != 0) {
            if (attrs.isReadable())
                bits |= READABLE;
            if (attrs.isWritable())
                bits |= WRITABLE;
            if (attrs.isExecutable())
                bits |= EXECUTABLE;
            if (attrs.isRandomAccessible())
                bits |= SEEKABLE;
        }

        if (0 != (mask & MASK_ATTRIB)) {
            if (0 != (mask & HIDDEN))
                if (attrs.isHidden())
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
            try {
                long length = this.getLength();
                if (length == 0)
                    bits |= EMPTY;
                if (length > 0)
                    bits |= NEMPTY;
            } catch (IOException e) {
            }
        }

        if (0 != (mask & MASK_OBJTYPE))
            if (isDirectory())
                bits |= DIRECTORY;
            else
                bits |= FILE;

        if (0 != (mask & CNTTYPE) && (bits & EXIST) != 0) {
            IProbing probe;
            try {
                probe = to(HeuristicProbing.class);
                if (probe.isText())
                    bits |= TEXT;
                else
                    bits |= BINARY;
            } catch (IOException e) {
                probe = to(LazyProbing.class);
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
    public final boolean renameTo(String target)
            throws BadPathException, IOException {
        IFile targetFile = resolve(target);
        return renameTo(targetFile);
    }

    public final boolean renameTo(IFile target)
            throws BadPathException, IOException {
        if (target == null)
            throw new NullPointerException("target");

        IVfsDevice srcDev = getDevice();
        IVfsDevice dstDev = target.getDevice();
        if (srcDev != dstDev)
            return false;
        if (!srcDev.equals(dstDev))
            return false;

        String srcPath = getPath().getLocalPath();
        String dstPath = target.getPath().getLocalPath();
        try {
            getDevice().move(srcPath, dstPath);
            return true;
        } catch (VFSException e) {
            return false;
        }
    }

    /** ⇱ Implementation Of {@link IFsBlob}. */
    /* _____________________________ */static section.iface __FS_BLOB__;

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
    public boolean setLength(long newLength)
            throws IOException {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean mkblob(boolean touch)
            throws IOException {
        return false;
    }

    /** ⇱ Implementation Of {@link IStreamResourceWrapper}. */
    /* _____________________________ */static section.iface __RESOURCE__;

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

    @Override
    public final IStreamResource getResource(Charset charset) {
        IStreamResource resource = newResource(charset);
        if (resource == null)
            return null;

        resource.addOpenResourceListener(new IOpenResourceListener() {
            @Override
            public void openResource(OpenResourceEvent event)
                    throws IOException {

                CommonOpenConfig config = CommonOpenConfig.parse(event.getOptions());

                if (config.isCreateParents()) {
                    IFile parent = getParentFile();
                    if (parent != null)
                        if (!parent.mkdirs())
                            throw new IOException("Can't create parents for " + this);
                }
            }
        });
        return resource;
    }

    /**
     * @return <code>null</code> If no resource available for this fs-entry.
     */
    protected abstract IStreamResource newResource(Charset charset);

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
        Charset charset = getPreferredCharset();
        if (charset == null)
            charset = LocaleColos.charset.get();
        // There no preferred-append-mode.
        return getOutputTarget(charset);
    }

    @Override
    public final IStreamOutputTarget getOutputTarget(String charsetName) {
        Charset charset = Charset.forName(charsetName);
        return getOutputTarget(charset);
    }

    @Override
    public IStreamOutputTarget getOutputTarget(Charset charset) {
        IStreamResource resource = getResource(charset);
        if (resource == null)
            throw new NullPointerException("resource");

        return resource;
    }

    /** ⇱ Implementaton Of {@link IFsDir}. */
    /* _____________________________ */static section.iface __FS_DIR__;

    @Override
    public boolean mkdir() {
        return isDirectory();
    }

    @Override
    public boolean mkdirs() {
        if (isDirectory())
            return true;

        if (isExisted())
            return mkdir();

        IFile parentFile = getParentFile();
        if (parentFile.mkdirs())
            return mkdir();
        else
            return false;
    }

    @Override
    public Iterable<? extends IFile> children()
            throws VFSException {
        return children(IFilenameFilter.TRUE);
    }

    /** ⇱ Implementation Of {@link IFileAttributes}. */
    /* _____________________________ */static section.iface __ATTRIBUTES__;

    @Override
    public boolean isBlob() {
        return isRegularFile();
    }

    @Override
    public long size() {
        try {
            return getLength();
        } catch (IOException e) {
            return -1L;
        }
    }

    /** ⇱ Implementation Of {@link Object}. */
    /* _____________________________ */static section.obj __OBJ__;

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
