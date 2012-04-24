package net.bodz.bas.c.object;

import java.util.Set;

public interface IDumpTreeContext {

    DumpTreeFormat getFormat();

    Set<Object> getOccurred();

    int getDepth();

}
