package net.bodz.bas.vfs;

import static net.bodz.bas.vfs.FileModifier.*;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

import net.bodz.bas.err.NotImplementedException;
import net.bodz.bas.i18n.LocaleColos;
import net.bodz.bas.io.resource.IStreamInputSource;
import net.bodz.bas.io.resource.IStreamOutputTarget;
import net.bodz.bas.io.resource.IStreamResource;
import net.bodz.bas.io.resource.tools.IStreamReading;
import net.bodz.bas.io.resource.tools.IStreamWriting;
import net.bodz.bas.io.resource.tools.StreamDumping;
import net.bodz.bas.io.resource.tools.StreamLoading;
import net.bodz.bas.io.resource.tools.StreamReading;
import net.bodz.bas.io.resource.tools.StreamWriting;
import net.bodz.bas.meta.codehint.GeneratedByCopyPaste;
import net.bodz.bas.util.iter.Iterators;
import net.bodz.bas.util.iter.Mitors;
import net.bodz.bas.util.iter.Mitorx;
import net.bodz.bas.vfs.path.BadPathException;
import net.bodz.bas.vfs.path.IPath;
import net.bodz.bas.vfs.preparation.HeuristicProbePreparation;
import net.bodz.bas.vfs.preparation.IProbePreparation;
import net.bodz.bas.vfs.preparation.LazyProbePreparation;
import net.bodz.bas.vfs.util.IFilenameFilter;

public abstract class AbstractFile
        extends AbstractFsEntry
        implements IFile {

    private Charset preferredCharset = Charset.defaultCharset();

    /**
     * @param volume
     *            non-<code>null</code> volume object which this file residues on.
     * @param path
     *            non-<code>null</code> path.
     */
    public AbstractFile(IVolume volume, IPath path) {
        super(volume, path);
    }

    /**
     * This is only used by {@link TransientVolume}.
     */
    AbstractFile(IPath path) {
        super(path);
    }

    /**
     * This is only used by {@link TransientPath}.
     */
    AbstractFile(String baseName) {
        super(baseName);
    }

    @Override
    public abstract IFile clone();

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
    public IFile getParentFile()
            throws FileResolveException {
        IPath parentPath = getPath().getParent();
        if (parentPath == null)
            return null;
        return parentPath.toFile();
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
            IProbePreparation probe;
            try {
                probe = forProbe(true);
                if (probe.isText())
                    bits |= TEXT;
                else
                    bits |= BINARY;
            } catch (IOException e) {
                probe = forProbe(false);
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
            try {
                IFile parent = getParentFile();
                if (parent != null)
                    parent.createTree();
            } catch (FileResolveException e) {
                throw new RuntimeException(e.getMessage(), e);
            }
        }
        IStreamResource resource = getResource(charset);
        resource.setAppendMode(appendMode);
        return resource;
    }

    @Override
    public IStreamReading forRead() {
        IStreamInputSource source = getInputSource();
        if (source == null)
            return null;
        return new StreamReading(source);
    }

    @Override
    public IStreamWriting forWrite() {
        IStreamOutputTarget target = getOutputTarget();
        if (target == null)
            return null;
        return new StreamWriting(target);
    }

    @Override
    public StreamLoading forLoad() {
        IStreamInputSource source = getInputSource();
        if (source == null)
            return null;
        return new StreamLoading(source);
    }

    @Override
    public StreamDumping forDump() {
        IStreamOutputTarget target = getOutputTarget();
        if (target == null)
            return null;
        return new StreamDumping(target);
    }

    // IFsTree

    @Override
    public boolean createTree() {
        return false;
    }

    @Override
    public boolean isIterable() {
        return false;
    }

    @Override
    public IFile getChild(String childName)
            throws FileResolveException {
        return null;
    }

    /**
     * @def Return an empty iterator.
     */
    @Override
    public Mitorx<? extends IFile, VFSException> childIterator(IFilenameFilter nameFilter)
            throws VFSException {
        return Mitors.empty();
    }

    /**
     * @def Return IteratorToList.toList(childIterator((IFilter<String>) null)).
     */
    @Override
    public List<? extends IFile> listChildren()
            throws VFSException {
        return Iterators.toList(childIterator((IFilenameFilter) null));
    }

    /**
     * @def Return as IteratorToList.toList(childIterator(entryNameFilter)).
     */
    @Override
    public List<? extends IFile> listChildren(IFilenameFilter entryNameFilter)
            throws VFSException {
        return Iterators.toList(childIterator(entryNameFilter));
    }

    @Override
    public IProbePreparation forProbe(boolean heuristic) {
        if (heuristic)
            return new HeuristicProbePreparation(this);
        else
            return new LazyProbePreparation(this);
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

    /**
     * Abstract implementation of {@link IFile}, with transient volume support.
     * <p>
     * This is only useful if you don't want to allocate an {@link IVolume} instance before it's
     * used. And construct one on-demand, for optimization purpose.
     */
    @GeneratedByCopyPaste(AbstractFsEntry.TransientVolume.class)
    public abstract static class TransientVolume
            extends AbstractFile {

        public TransientVolume(IPath path) {
            super(path);
        }

        /**
         * This constructor is only used by {@link TransientPath}.
         */
        TransientVolume(String baseName) {
            super(baseName);
        }

        @Override
        public abstract IVolume getVolume();

    }

    /**
     * Abstract implementation of {@link IFile}, with transient path support.
     * <p>
     * This is only useful if you don't want to allocate a {@link IPath} instance before it's used.
     * And construct one on-demand, for optimization purpose.
     */
    @GeneratedByCopyPaste(AbstractFsEntry.TransientPath.class)
    public abstract static class TransientPath
            extends TransientVolume {

        protected final String pathString;

        /**
         * Creates a new fs entry with transient path.
         * 
         * The <code>pathString</code> should be in correct format to keep this fs entry in a good
         * state. Otherwise, {@link IllegalStateException} may be thrown when {@link #getPath()} is
         * called.
         * 
         * @param pathString
         *            non-<code>null</code> path string.
         */
        public TransientPath(String pathString) {
            super(getBaseName(pathString));
            this.pathString = pathString;
        }

        static String getBaseName(String path) {
            int lastSlash = path.lastIndexOf('/');
            if (lastSlash == -1)
                return path;
            else
                return path.substring(lastSlash + 1);
        }

        @Override
        public abstract IVolume getVolume();

        /**
         * Constructs an {@link IPath} object on the fly.
         * 
         * @throws IllegalStateException
         *             If the <code>pathString</code> specified in the constructor is invalid.
         */
        @Override
        public IPath getPath() {
            try {
                return constructPath(pathString);
            } catch (BadPathException e) {
                throw new IllegalStateException(e.getMessage(), e);
            }
        }

        /**
         * @throws BadPathException
         *             If <code>pathString</code> is invalid.
         */
        protected IPath constructPath(String pathString)
                throws BadPathException {
            throw new NotImplementedException();
        }

        @Override
        public String toString() {
            return pathString;
        }

    }

}
