package net.bodz.bas.vfs;

import java.util.List;

import net.bodz.bas.model.IFilter;
import net.bodz.bas.sugar.IToolable;
import net.bodz.bas.util.iter.Mitorx;
import net.bodz.bas.vfs.util.IFilenameFilter;

public interface IFile
        extends IFsBlob, IFsTree, IToolable {

    @Override
    IFile clone();

    @Override
    IFile getParentFile();

    @Override
    IFile getChild(String childName)
            throws FileResolveException;

    @Override
    Mitorx<? extends IFile, VFSException> childIterator(IFilenameFilter nameFilter)
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
    List<? extends IFile> listChildren(IFilenameFilter nameFilter)
            throws VFSException;

}
