package net.bodz.bas.vfs;

import java.io.IOException;
import java.util.List;

import net.bodz.bas.closure.IFilter;
import net.bodz.bas.collection.iterator.ImmediateIteratorX;

public interface IFile
        extends IFileFeature, IFolder {

    @Override
    IFile clone();

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

}
