package net.bodz.bas.vfs;

import java.util.List;

import net.bodz.bas.closure.IFilter;
import net.bodz.bas.collection.iterator.ImmediateIteratorX;
import net.bodz.bas.vfs.preparation.IProbePreparation;

public interface IFile
        extends IFsBlob, IFsTree {

    @Override
    IFile clone();

    @Override
    IFile getParentFile()
            throws FileResolveException;

    @Override
    IFile getChild(String childName)
            throws VFSException;

    @Override
    ImmediateIteratorX<? extends IFile, VFSException> childIterator(IFilter<String> nameFilter)
            throws VFSException;

    /**
     * List all children files.
     * <p>
     * To ignore hidden files, pass an file system option, or filtered them using
     * {@link #listChildren(IFilter)}.
     * 
     * @return List of all children files.
     *         <p>
     *         The behavior of modifying the list is undetermined.
     * @throws VFSException
     *             If failed to list.
     *             <p>
     *             This maybe caused by no permission.
     */
    @Override
    List<? extends IFile> listChildren()
            throws VFSException;

    /**
     * List children files with a name filter.
     * 
     * @return List of children files which passed the filter.
     *         <p>
     *         The behavior of modifying the list is undetermined.
     * @throws VFSException
     *             If failed to list.
     *             <p>
     *             This maybe caused by no permission.
     */
    @Override
    List<? extends IFile> listChildren(IFilter<String> nameFilter)
            throws VFSException;

    /**
     * There are two probe modes: mime-based probe and heuristic-based probe.
     * 
     * <ul>
     * <li>The mime-based probe will look at the file meta info.
     * <li>The heuristic-based probe will look into the file contents.
     * </ul>
     * 
     * @param heuristic
     *            <code>true</code> to work in heuristic mode.
     * @return non-<code>null</code> {@link IProbePreparation} object.
     */
    IProbePreparation forProbe(boolean heuristic);

}
