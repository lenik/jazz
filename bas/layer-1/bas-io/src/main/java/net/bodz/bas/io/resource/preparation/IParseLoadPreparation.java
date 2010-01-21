package net.bodz.bas.io.resource.preparation;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

import net.bodz.bas.collection.iterator.ImmediateIteratorX;

public interface IParseLoadPreparation
        extends Cloneable {

    IParseLoadPreparation clone();

    Properties loadProperties()
            throws IOException;

    List<? extends Object> listObjects(int maxItems)
            throws IOException;

    ImmediateIteratorX<? extends Object, ? extends IOException> objectIterator()
            throws IOException;

}
