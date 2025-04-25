package net.bodz.bas.io;

import java.io.IOException;

public interface ILookAhead<T> {

    int getLookCapacity();

    int getLookLimit();

    /**
     * @return null if EOF
     */
    T look()
            throws IOException;

}
