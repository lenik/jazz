package net.bodz.bas.fs.preparation;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

import net.bodz.bas.collection.iterator.ImmediateIteratorX;

public interface IParseLoadPreparation {

    Properties loadProperties()
            throws IOException;

    List<Object> listObjects(int maxItems)
            throws IOException;

    ImmediateIteratorX<Object, IOException> objectIterator()
            throws IOException;

}
