package net.bodz.bas.vfs;

import java.io.IOException;
import java.util.List;

import net.bodz.bas.closure.IFilter;
import net.bodz.bas.collection.iterator.ImmediateIteratorX;

public interface IFsTree
        extends IFsEntry {

    @Override
    IFsTree clone();

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

    /**
     * Create the tree represent by this object, along with all parents which are not existed.
     * 
     * @return <code>true</code> If the tree is existed or succeeded to create.
     */
    boolean createTree();

}
