package net.bodz.bas.vfs;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

import net.bodz.bas.closure.IFilter;
import net.bodz.bas.collection.iterator.EmptyImmediateIteratorX;
import net.bodz.bas.collection.iterator.ImmediateIteratorX;
import net.bodz.bas.collection.util.IteratorToList;
import net.bodz.bas.exceptions.NotImplementedException;
import net.bodz.bas.hint.GeneratedByCopyPaste;
import net.bodz.bas.io.resource.IStreamInputSource;
import net.bodz.bas.io.resource.IStreamOutputTarget;
import net.bodz.bas.io.resource.preparation.FormatDumpPreparation;
import net.bodz.bas.io.resource.preparation.IStreamReadPreparation;
import net.bodz.bas.io.resource.preparation.IStreamWritePreparation;
import net.bodz.bas.io.resource.preparation.ParseLoadPreparation;
import net.bodz.bas.io.resource.preparation.StreamReadPreparation;
import net.bodz.bas.io.resource.preparation.StreamWritePreparation;
import net.bodz.bas.vfs.path.BadPathException;
import net.bodz.bas.vfs.path.IPath;
import net.bodz.bas.vfs.preparation.HeuristicProbePreparation;
import net.bodz.bas.vfs.preparation.IProbePreparation;
import net.bodz.bas.vfs.preparation.LazyProbePreparation;

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
    public IFile getParentFile() {
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
    public long getLength() {
        return 0;
    }

    @Override
    public IStreamInputSource toSource() {
        if (isBlob())
            throw new NotImplementedException();
        return null;
    }

    @Override
    public IStreamOutputTarget toTarget() {
        if (isBlob())
            throw new NotImplementedException();
        return null;
    }

    @Override
    public IStreamReadPreparation forRead() {
        IStreamInputSource source = toSource();
        if (source == null)
            return null;
        return new StreamReadPreparation(source);
    }

    @Override
    public IStreamWritePreparation forWrite() {
        IStreamOutputTarget target = toTarget();
        if (target == null)
            return null;
        return new StreamWritePreparation(target);
    }

    @Override
    public ParseLoadPreparation forLoad() {
        IStreamInputSource source = toSource();
        if (source == null)
            return null;
        return new ParseLoadPreparation(source);
    }

    @Override
    public FormatDumpPreparation forDump() {
        IStreamOutputTarget target = toTarget();
        if (target == null)
            return null;
        return new FormatDumpPreparation(target);
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
            throws IOException {
        return null;
    }

    /**
     * @def Return an empty iterator.
     */
    @Override
    public ImmediateIteratorX<? extends IFile, IOException> childIterator(IFilter<String> nameFilter)
            throws IOException {
        return EmptyImmediateIteratorX.getInstance();
    }

    /**
     * @def Return IteratorToList.toList(childIterator((IFilter<String>) null)).
     */
    @Override
    public List<? extends IFile> listChildren()
            throws IOException {
        return IteratorToList.toList(childIterator((IFilter<String>) null));
    }

    /**
     * @def Return as IteratorToList.toList(childIterator(entryNameFilter)).
     */
    @Override
    public List<? extends IFile> listChildren(IFilter<String> entryNameFilter)
            throws IOException {
        return IteratorToList.toList(childIterator(entryNameFilter));
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
