package net.bodz.bas.io;

import java.io.IOException;

public interface IByteOut {

    void write(byte[] buf, int off, int len) throws IOException;

}
