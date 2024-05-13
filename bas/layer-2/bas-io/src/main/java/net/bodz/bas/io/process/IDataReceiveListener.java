package net.bodz.bas.io.process;

import java.io.IOException;

@FunctionalInterface
public interface IDataReceiveListener {

    void onDataReceived(byte[] buf, int off, int len)
            throws IOException;

}
