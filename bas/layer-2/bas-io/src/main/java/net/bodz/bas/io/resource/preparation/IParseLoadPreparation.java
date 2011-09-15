package net.bodz.bas.io.resource.preparation;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

import net.bodz.bas.util.iter.Mitorx;

public interface IParseLoadPreparation
        extends Cloneable {

    IParseLoadPreparation clone();

    Properties loadProperties()
            throws IOException;

    List<? extends Object> listObjects(int maxItems)
            throws IOException;

    Mitorx<? extends Object, ? extends IOException> objectIterator()
            throws IOException;

}
