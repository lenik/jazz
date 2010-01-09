package net.bodz.bas.fs;

import java.io.IOException;
import java.util.List;

import net.bodz.bas.closure.IFilter;
import net.bodz.bas.collection.iterator.ImmediateIteratorX;

public interface IFolder
        extends IEntry, Cloneable {

    IEntry getEntry(String entryName)
            throws IOException;

    ImmediateIteratorX<? extends IEntry, IOException> entryIterator(IFilter<String> entryNameFilter);

    List<? extends IEntry> listEntries()
            throws IOException;

    List<? extends IEntry> listEntries(IFilter<String> entryNameFilter)
            throws IOException;

}
