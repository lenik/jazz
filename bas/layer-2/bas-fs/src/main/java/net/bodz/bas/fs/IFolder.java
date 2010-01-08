package net.bodz.bas.fs;

import java.io.IOException;

import net.bodz.bas.collection.iterator.ImmediateIterator;

public interface IFolder
        extends IEntry, Cloneable {

    IFile getFileEntry(String entryName)
            throws IOException;

    IFolder getFolderEntry(String entryName)
            throws IOException;

    ImmediateIterator<IEntry, IOException> entryIterator(IEntryFilter filter);

    ImmediateIterator<IEntry, IOException> entryIterator(IEntryNameFilter filter);

    ImmediateIterator<String, IOException> entryNameIterator(IEntryNameFilter filter);

}
