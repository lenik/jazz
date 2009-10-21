package net.bodz.bas.io;

import java.io.IOException;

public interface Lookable {

    int getLookMax();

    int getLookedLength();

    int look() throws IOException;

    int look(char[] buf) throws IOException;

    int look(char[] cbuf, int off, int len) throws IOException;

    String look(int len) throws IOException;

}
