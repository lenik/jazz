package net.bodz.bas.fs;

import java.io.IOException;
import java.util.Collection;

public interface IDumpToolkit {

    void dumpObject(Object o)
            throws IOException;

    void dumpObjects(Collection<Object> objects)
            throws IOException;

    void dumpXML(Object o)
            throws IOException;

}
