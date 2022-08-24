package net.bodz.bas.vfs;

import java.io.IOException;
import java.nio.file.NotLinkException;

import net.bodz.bas.fn.IFilter;
import net.bodz.bas.io.res.tools.IStreamReading;
import net.bodz.bas.io.res.tools.IStreamWriting;
import net.bodz.bas.io.res.tools.StreamReading;
import net.bodz.bas.io.res.tools.StreamWriting;
import net.bodz.bas.sugar.IToChain;
import net.bodz.bas.t.iterator.Iterables;
import net.bodz.bas.vfs.path.BadPathException;

public interface IFile
        extends
            IFsBlob,
            IFsDir,
            IToChain {

    @Override
    IFile getParentFile();

    /**
     * @throws NullPointerException
     *             If <code>childName</code> is <code>null</code>.
     */
    @Override
    IFile getChild(String childName);

    /**
     * @throws UnsupportedOperationException
     *             If not iterable.
     * @see #isTraversible()
     */
    @Override
    Iterable<? extends IFile> children()
            throws VFSException;

    /**
     * @throws UnsupportedOperationException
     *             If not iterable.
     * @see #isTraversible()
     */
    @Override
    Iterable<? extends IFile> children(IFilenameFilter nameFilter)
            throws VFSException;

    /**
     * @throws UnsupportedOperationException
     *             If not iterable.
     * @see #isTraversible()
     */
    Iterable<? extends IFile> children(IFilter<IFile> fileFilter)
            throws VFSException;

    /**
     * @param relativePath
     *            The path should be within the same scope provided by the device.
     */
    IFile resolve(String relativePath)
            throws BadPathException, FileResolveException;

    /**
     * @param relativePath
     *            The path should be within the same scope provided by the device.
     */
    IFile resolve(String relativePath, FileResolveOptions options)
            throws BadPathException, FileResolveException;

    /**
     * Create a (symbolic) link to the target.
     *
     * @param targetPath
     *            Relative path string to the target.
     * @param symbolic
     *            Create a symbolic link rather then hard link.
     * @return <code>false</code> If file is already existed.
     * @throws UnsupportedOperationException
     *             If the underlying device doesn't support symbolic link.
     * @throws BadPathException
     *             If <code>targetSpec</code> is invalid.
     */
    boolean linkTo(String target, boolean symbolic)
            throws IOException;

    /**
     * Read the target spec of the symbolic link.
     *
     * @throws UnsupportedOperationException
     *             If the underlying device doesn't support symbolic link.
     * @throws NotLinkException
     *             If this file isn't a symlink.
     */
    String readSymbolicLink()
            throws IOException;

    default IStreamReading read() {
        return new StreamReading(getInputSource());
    }

    default IStreamWriting write() {
        return new StreamWriting(getOutputTarget());
    }

    class fn {

        public static Iterable<? extends IFile> children(final IFile parent, final IFilenameFilter nameFilter)
                throws VFSException {
            return Iterables.filter(parent.children(), new IFilter<IFile>() {
                @Override
                public boolean accept(IFile o)
                        throws RuntimeException {
                    // assert parent == o.getParentFile();
                    String name = o.getName();
                    return nameFilter.accept(parent, name);
                }
            });
        }

    }

}
