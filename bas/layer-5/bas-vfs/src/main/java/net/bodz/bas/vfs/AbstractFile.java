package net.bodz.bas.vfs;

import static net.bodz.bas.vfs.FileFlags.*;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.attribute.BasicFileAttributes;

import net.bodz.bas.c.java.nio.CommonOpenConfig;
import net.bodz.bas.c.java.nio.DeviceAttributes;
import net.bodz.bas.c.java.nio.FilePermissionAttributes;
import net.bodz.bas.err.UnexpectedException;
import net.bodz.bas.i18n.LocaleColos;
import net.bodz.bas.io.res.IOpenResourceListener;
import net.bodz.bas.io.res.IStreamInputSource;
import net.bodz.bas.io.res.IStreamOutputTarget;
import net.bodz.bas.io.res.IStreamResource;
import net.bodz.bas.io.res.OpenResourceEvent;
import net.bodz.bas.sugar.Tooling;
import net.bodz.bas.vfs.path.BadPathException;
import net.bodz.bas.vfs.path.IPath;
import net.bodz.bas.vfs.util.content.HeuristicProbing;
import net.bodz.bas.vfs.util.content.IProbing;
import net.bodz.bas.vfs.util.content.LazyProbing;

public abstract class AbstractFile
        extends AbstractFsEntry
        implements IFile /* , Serializable */{

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
    public final boolean isExecutable() {
        try {
            FilePermissionAttributes attrs = this.readAttributes(FilePermissionAttributes.class);
            return attrs == null ? false : attrs.isExecutable();
        } catch (IOException e) {
            return false;
        }
    }

    @Override
    public final boolean isSeekable() {
        try {
            DeviceAttributes attrs = this.readAttributes(DeviceAttributes.class);
            return attrs == null ? false : attrs.isRandomAccessible();
        } catch (IOException e) {
            return false;
        }
    }

    @Override
    public Long getLength() {
        try {
            BasicFileAttributes attrs = this.readAttributes(BasicFileAttributes.class);
            return attrs == null ? null : attrs.size();
        } catch (IOException e) {
            return null;
        }
    }

    @Override
    public final long length() {
        Long length = getLength();
        if (length == null)
            return 0;
        else
            return length.longValue();
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
            if (isDirectory())
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
    public final boolean renameTo(String destSpec)
            throws BadPathException, IOException {
        IFile dest = resolve(destSpec);
        return renameTo(dest);
    }

    public final boolean renameTo(IFile dest)
            throws BadPathException, IOException {
        if (dest == null)
            throw new NullPointerException("dest");

        IVfsDevice srcDev = getDevice();
        IVfsDevice destDev = dest.getDevice();
        if (srcDev != destDev)
            return false;
        if (!srcDev.equals(destDev))
            return false;

        String fromLocalPath = getPath().getLocalPath();
        String destLocalPath = dest.getPath().getLocalPath();
        try {
            getDevice().move(fromLocalPath, destLocalPath);
            return true;
        } catch (VFSException e) {
            return false;
        }
    }

    /** ⇱ Implementaton Of {@link IFsBlob}. */
    ;

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
    ;

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
    public boolean isIterable() {
        return isDirectory();
    }

    @Override
    public Iterable<? extends IFile> children()
            throws VFSException {
        return children(IFilenameFilter.TRUE);
    }

    @Override
    public IFile resolve(String spec)
            throws BadPathException, FileResolveException {
        IPath joinedPath = getPath().join(spec);
        IFile file = VFS.resolve(joinedPath);
        return file;
    }

    /** ⇱ Implementaton Of {@link net.bodz.bas.sugar.IToolable}. */
    ;

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
