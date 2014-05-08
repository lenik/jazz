package net.bodz.bas.c.java.io;

import java.io.IOException;

public interface ILineReader
        extends Iterable<String> {

    String readLine()
            throws IOException;

    void close()
            throws IOException;

}
