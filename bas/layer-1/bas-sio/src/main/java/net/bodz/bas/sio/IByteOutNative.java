package net.bodz.bas.sio;

import java.io.IOException;

public interface IByteOutNative
        extends IByteOut {

    void write(boolean b)
            throws IOException;

}
