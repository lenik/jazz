package net.bodz.bas.sys;

import java.io.IOException;

import net.bodz.bas.io.ByteOut;

public interface IOCallback {

    void sendProc(ByteOut out) throws IOException;

    /**
     * Process data received from stdout of the process
     */
    void recvIn(byte[] buf, int off, int len) throws IOException;

    /**
     * Process data received from stderr of the process
     */
    void recvErr(byte[] buf, int off, int len) throws IOException;

}
