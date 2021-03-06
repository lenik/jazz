package net.bodz.bas.io.res.tools;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

import net.bodz.bas.t.iterator.immed.Mitorx;
import net.bodz.bas.t.object.IContextUtility;

public interface IStreamLoading
        extends IContextUtility {

    @Override
    IStreamLoading clone();

    Properties loadProperties()
            throws IOException;

    List<? extends Object> listObjects(int maxItems)
            throws IOException;

    Mitorx<? extends Object, ? extends IOException> objectIterator()
            throws IOException;

}
