package net.bodz.bas.sys;

import java.io.IOException;
import java.io.OutputStream;

public interface IOCallback {

    void sendProc(OutputStream out) throws IOException;

    /**
     * Process data received from stdout of the process
     */
    void recvIn(byte[] buf, int off, int len) throws IOException;

    /**
     * Process data received from stderr of the process
     */
    void recvErr(byte[] buf, int off, int len) throws IOException;

}
