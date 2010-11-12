package net.bodz.bas.vfs;

import java.io.IOException;
import java.util.List;

import net.bodz.bas.closure.IFilter;
import net.bodz.bas.collection.iterator.ImmediateIteratorX;

public interface IFolder
        extends IFsEntry {

    @Override
    IFolder clone();

    boolean isIterable();

    /**
     * @return <code>null</code> If <code>childName</code> isn't existed, or the folder isn't
     *         accessible.
     */
    IFsEntry getChild(String childName)
            throws IOException;

    /**
     * @throws UnsupportedOperationException
     *             If not iterable.
     * @see #isIterable()
     */
    ImmediateIteratorX<? extends IFsEntry, IOException> childIterator(IFilter<String> nameFilter)
            throws IOException;

    /**
     * @throws UnsupportedOperationException
     *             If not iterable.
     * @see #isIterable()
     */
    List<? extends IFsEntry> listChildren()
            throws IOException;

    /**
     * @throws UnsupportedOperationException
     *             If not iterable.
     * @see #isIterable()
     */
    List<? extends IFsEntry> listChildren(IFilter<String> nameFilter)
            throws IOException;

}
