package net.bodz.bas.io.adapter;

import java.io.IOException;
import java.io.Writer;

import net.bodz.bas.io.ICharOut;
import net.bodz.bas.meta.decl.NotNull;

public class StringBuilderCharOut
        extends Writer
        implements ICharOut {

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
    public void write(@NotNull char[] chars, int off, int len)
            throws IOException {
        sb.append(chars, off, len);
    }

    @Override
    public void write(@NotNull CharSequence chars, int start, int end)
            throws IOException {
        sb.append(chars, start, end - start);
    }

    @Override
    public void flush() {
    }

    @Override
    public void close() {
    }

}
