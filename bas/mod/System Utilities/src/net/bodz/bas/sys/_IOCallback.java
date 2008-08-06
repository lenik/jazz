package net.bodz.bas.sys;

import java.io.IOException;
import java.io.InputStream;

import net.bodz.bas.io.ByteOut;

public abstract class _IOCallback implements IOCallback {

    static int BLOCK = 4096;

    protected void sendProc(ByteOut out, InputStream in) throws IOException {
        byte[] buf = new byte[BLOCK];
        int cb;
        while ((cb = in.read(buf)) != -1)
            out._write(buf, 0, cb);
    }

}
