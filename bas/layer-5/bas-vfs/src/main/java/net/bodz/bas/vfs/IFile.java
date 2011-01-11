package net.bodz.bas.vfs;

import java.io.IOException;
import java.util.List;

import net.bodz.bas.closure.IFilter;
import net.bodz.bas.collection.iterator.ImmediateIteratorX;
import net.bodz.bas.vfs.preparation.IProbePreparation;

public interface IFile
        extends IFsBlob, IFsTree {

    @Override
    IFile clone();

    @Override
    IFile getParentFile();

    @Override
    IFile getChild(String childName)
            throws IOException;

    @Override
    ImmediateIteratorX<? extends IFile, IOException> childIterator(IFilter<String> nameFilter)
            throws IOException;

    @Override
    List<? extends IFile> listChildren()
            throws IOException;

    @Override
    List<? extends IFile> listChildren(IFilter<String> nameFilter)
            throws IOException;

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
