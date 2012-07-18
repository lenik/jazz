package net.bodz.bas.io.resource.tools;

import java.io.IOException;
import java.util.Collection;

public interface IStreamDumping
        extends Cloneable {

    IStreamDumping clone();

    void dumpObject(Object o)
            throws IOException;

    void dumpObjects(Collection<?> objects)
            throws IOException;

    void dumpXML(Object o)
            throws IOException;

}
