package net.bodz.bas.sio;

import java.io.IOException;
import java.nio.CharBuffer;

public class NullCharOut
        extends AbstractCharOut {

    @Override
    public void write(int ch)
            throws IOException {
    }

    @Override
    public void write(char[] chars)
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

    @Override
    public void write(String s)
            throws IOException {
    }

    @Override
    public void write(String string, int off, int len)
            throws IOException {
    }

    @Override
    public void write(CharSequence chars, int off, int len)
            throws IOException {
    }

    static final NullCharOut instance = new NullCharOut();

    public static NullCharOut getInstance() {
        return instance;
    }

}
