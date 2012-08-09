package net.bodz.bas.flow.unit.builtins;

import java.io.IOException;
import java.nio.CharBuffer;

import net.bodz.bas.flow.unit.GenericUnit_1z;

public abstract class TextProcessSinkUnit
        extends GenericUnit_1z {

    public abstract void recv(char[] chars, int start, int end)
            throws IOException;

    public void recv(char[] chars)
            throws IOException {
        recv(chars, 0, chars.length);
    }

    public void recv(String string)
            throws IOException {
        recv(string.toCharArray());
    }

    public void recv(CharBuffer chars)
            throws IOException {
        recv(chars.array(), chars.position(), chars.limit());
    }

}
