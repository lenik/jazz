package net.bodz.bas.io.capture;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public abstract class _IOCallback implements IOCallback {

    static int BLOCK = 4096;

    protected void sendProc(OutputStream out, InputStream in) throws IOException {
        byte[] buf = new byte[BLOCK];
        int cb;
        while ((cb = in.read(buf)) != -1) {
            out.write(buf, 0, cb);
            out.flush();
        }
        out.close();
    }

}
