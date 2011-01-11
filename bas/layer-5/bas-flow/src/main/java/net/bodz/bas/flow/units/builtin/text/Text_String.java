package net.bodz.bas.flow.units.builtin.text;

import java.io.IOException;

import net.bodz.bas.flow.Stateless;

@Stateless
public class Text_String
        extends TextProcessUnit {

    @Override
    public void reset()
            throws IOException {
    }

    @Override
    public void flush()
            throws IOException {
    }

    @Override
    public void recv(char[] chars, int start, int end)
            throws IOException {
        recv(new String(chars, start, end - start));
    }

    @Override
    public void recv(String string)
            throws IOException {
        send(string);
    }

}
