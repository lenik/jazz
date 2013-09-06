package net.bodz.bas.io.res.tools;

import java.io.IOException;
import java.util.Collection;

import net.bodz.bas.t.object.IContextUtility;

public interface IStreamDumping
        extends IContextUtility {

    @Override
    IStreamDumping clone();

    void dumpObject(Object o)
            throws IOException;

    void dumpObjects(Collection<?> objects)
            throws IOException;

    void dumpXML(Object o)
            throws IOException;

}
