package net.bodz.bas.fs;

import java.io.IOException;
import java.util.List;

import net.bodz.bas.closure.IFilter;
import net.bodz.bas.collection.iterator.ImmediateIteratorX;

public interface IFolder
        extends IFsEntry, Cloneable {

    boolean isIterable();

    /**
     * @return <code>null</code> If <code>entryName</code> isn't existed, or the folder isn't
     *         accessible.
     */
    IFsEntry getEntry(String entryName)
            throws IOException;

    /**
     * @throws UnsupportedOperationException
     *             If not iterable.
     * @see #isIterable()
     */
    ImmediateIteratorX<? extends IFsEntry, IOException> entryIterator(IFilter<String> entryNameFilter)
            throws IOException;

    /**
     * @throws UnsupportedOperationException
     *             If not iterable.
     * @see #isIterable()
     */
    List<? extends IFsEntry> listEntries()
            throws IOException;

    /**
     * @throws UnsupportedOperationException
     *             If not iterable.
     * @see #isIterable()
     */
    List<? extends IFsEntry> listEntries(IFilter<String> entryNameFilter)
            throws IOException;

}
