package net.bodz.bas.net.io;

import net.bodz.bas.io.ILookBytesAhead;

public interface IReadResult {

    long getNumBytesRead();

    boolean isReadEnd();

    default boolean nop() {
        return getNumBytesRead() == 0 && !isReadEnd();
    }

    ILookBytesAhead look();

}
