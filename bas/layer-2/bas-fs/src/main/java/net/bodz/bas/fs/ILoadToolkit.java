package net.bodz.bas.fs;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

import net.bodz.bas.collection.iterator.ImmediateIteratorX;

public interface ILoadToolkit {

    Properties loadProperties()
            throws IOException;

    List<Object> listObjects(int maxItems)
            throws IOException;

    ImmediateIteratorX<Object, IOException> objectIterator()
            throws IOException;

}
