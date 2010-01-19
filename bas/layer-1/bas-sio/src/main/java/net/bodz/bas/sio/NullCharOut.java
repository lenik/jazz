package net.bodz.bas.sio;

import java.io.IOException;
import java.nio.CharBuffer;

public class NullCharOut
        extends CharOut {

    @Override
    public void write(int ch)
            throws IOException {
    }

    @Override
    public void write(char[] chars, int off, int len)
            throws IOException {
    }

    @Override
    public void write(CharBuffer charBuffer)
            throws IOException {
    }

    static final NullCharOut instance = new NullCharOut();

    public static NullCharOut getInstance() {
        return instance;
    }

}
