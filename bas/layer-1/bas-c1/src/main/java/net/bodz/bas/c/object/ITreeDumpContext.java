package net.bodz.bas.c.object;

import java.util.Set;

public interface ITreeDumpContext {

    TreeDumpFormat getFormat();

    Set<Object> getOccurred();

    int getDepth();

}
