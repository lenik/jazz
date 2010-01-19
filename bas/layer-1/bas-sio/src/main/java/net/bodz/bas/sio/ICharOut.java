package net.bodz.bas.sio;

import java.io.IOException;
import java.nio.CharBuffer;

public interface ICharOut
        extends ISimpleCharOut {

    void write(char[] chars, int off, int len)
            throws IOException;

    public void write(String s, int off, int len)
            throws IOException;

    public void write(CharSequence chars, int off, int len)
            throws IOException;

    void write(CharBuffer charBuffer)
            throws IOException;

    void flush()
            throws IOException;

}
