package net.bodz.bas.io.resource.tools;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

import net.bodz.bas.t.iterator.immed.Mitorx;

public interface IStreamLoading
        extends Cloneable {

    IStreamLoading clone();

    Properties loadProperties()
            throws IOException;

    List<? extends Object> listObjects(int maxItems)
            throws IOException;

    Mitorx<? extends Object, ? extends IOException> objectIterator()
            throws IOException;

}
