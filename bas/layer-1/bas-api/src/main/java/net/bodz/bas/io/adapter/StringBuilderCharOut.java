package net.bodz.bas.io.adapter;

import java.io.IOException;

import net.bodz.bas.io.AbstractCharOut;

public class StringBuilderCharOut
        extends AbstractCharOut {

    private final StringBuilder sb;

    public StringBuilderCharOut(StringBuilder sb) {
        this.sb = sb;
    }

    @Override
    public void write(int c)
            throws IOException {
        sb.append((char) c);
    }

    @Override
    public void write(char[] chars, int off, int len)
            throws IOException {
        sb.append(chars, off, len);
    }

    @Override
    public void write(CharSequence chars, int off, int len)
            throws IOException {
        sb.append(chars, off, off + len);
    }

}