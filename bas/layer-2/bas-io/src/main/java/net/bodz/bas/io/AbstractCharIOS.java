package net.bodz.bas.io;

import java.io.IOException;
import java.io.Writer;
import java.nio.CharBuffer;

import net.bodz.bas.io.adapter.CharOutWriter;

public abstract class AbstractCharIOS
        extends AbstractCloseable
        implements ICharIOS {

    /** ⇱ Implementation Of {@link ICharIn}. */
    /* _____________________________ */static section.iface __IN__;

    @Override
    public final int read(char[] dst)
            throws IOException {
        return read(dst, 0, dst.length);
    }

    @Override
    public int read(CharBuffer buf)
            throws IOException {
        return ICharIn.fn.read(this, buf);
    }

    @Override
    public String readString(int maxCharacters)
            throws IOException {
        return ICharIn.fn.readString(this, maxCharacters);
    }

    /** ⇱ Implementation Of {@link ICharOut}. */
    /* _____________________________ */static section.iface __OUT__;

    @Override
    public final void write(char[] buf)
            throws IOException {
        write(buf, 0, buf.length);
    }

    @Override
    public void write(CharSequence chars)
            throws IOException {
        write(chars, 0, chars.length());
    }

    @Override
    public void write(CharSequence chars, int start, int end)
            throws IOException {
        for (int i = start; i < end; i++)
            write(chars.charAt(i));
    }

    @Override
    public void write(String s)
            throws IOException {
        write(s.toCharArray());
    }

    @Override
    public void write(String string, int off, int len)
            throws IOException {
        char[] buf = new char[len];
        string.getChars(off, off + len, buf, 0);
        write(buf, 0, len);
    }

    @Override
    public void write(CharBuffer buf)
            throws IOException {
        ICharOut.fn.write(this, buf);
    }

    @Override
    public void flush()
            throws IOException {
    }

    @Override
    public Writer toWriter() {
        return new CharOutWriter(this);
    }

}
