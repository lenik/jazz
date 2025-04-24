package net.bodz.bas.io.adapter;

import java.io.IOException;
import java.io.Reader;
import java.nio.CharBuffer;

import net.bodz.bas.io.ICharIn;
import net.bodz.bas.meta.decl.NotNull;

public class ReaderCharIn
        extends Reader
        implements ICharIn {

    private final Reader reader;

    /**
     * @throws NullPointerException If <code>reader</code> is <code>null</code>.
     */
    public ReaderCharIn(Reader reader) {
        if (reader == null)
            throw new NullPointerException("reader");
        this.reader = reader;
    }

    @Override
    public int read()
            throws IOException {
        return reader.read();
    }

    @Override
    public int read(@NotNull char[] chars, int off, int len)
            throws IOException {
        return reader.read(chars, off, len);
    }

    @Override
    public int read(@NotNull CharBuffer buffer)
            throws IOException {
        return reader.read(buffer);
    }

    @Override
    public void close()
            throws IOException {
        reader.close();
    }

    public Reader toReader() {
        return reader;
    }

}
