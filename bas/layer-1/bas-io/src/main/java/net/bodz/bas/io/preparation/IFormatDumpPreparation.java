package net.bodz.bas.io.preparation;

import java.io.IOException;
import java.util.Collection;

public interface IFormatDumpPreparation {

    void dumpObject(Object o)
            throws IOException;

    void dumpObjects(Collection<?> objects)
            throws IOException;

    void dumpXML(Object o)
            throws IOException;

}
