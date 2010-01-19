package net.bodz.bas.sio;

import java.io.IOException;
import java.nio.ByteBuffer;

public class NullByteOut
        extends ByteOut {

    @Override
    public void write(int b) {
    }

    @Override
    public void write(byte[] buf, int off, int len) {
    }

    @Override
    public void write(ByteBuffer buffer)
            throws IOException {
    }

    static final NullByteOut instance = new NullByteOut();

    public static NullByteOut getInstance() {
        return instance;
    }

}
