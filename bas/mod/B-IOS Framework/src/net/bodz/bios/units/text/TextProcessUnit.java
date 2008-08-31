package net.bodz.bios.units.text;

import java.io.IOException;
import java.nio.CharBuffer;

import net.bodz.bas.lang.util.MethodEx;
import net.bodz.bios.units.SISOUnit;

public abstract class TextProcessUnit extends SISOUnit {

    public abstract void recv(char[] chars, int start, int end)
            throws IOException;

    public void recv(char[] chars) throws IOException {
        recv(chars, 0, chars.length);
    }

    /**
     * {@link CharSequence} is interface, which isn't supported by
     * {@link MethodEx}.
     */
    public void recv(String string) throws IOException {
        recv(string.toCharArray());
    }

    public void recv(CharBuffer chars) throws IOException {
        recv(chars.array(), chars.position(), chars.limit());
    }

}
