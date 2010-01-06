package net.bodz.bas.io.out;

import java.io.IOException;

public interface IByteOut {

    void write(byte[] buf, int off, int len) throws IOException;

}
