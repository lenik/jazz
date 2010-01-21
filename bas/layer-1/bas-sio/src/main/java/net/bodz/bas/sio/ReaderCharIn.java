package net.bodz.bas.sio;

import java.io.IOException;
import java.io.Reader;
import java.nio.CharBuffer;

public class ReaderCharIn
        extends AbstractCharIn {

    private final Reader reader;

    /**
     * @throws NullPointerException
     *             If <code>reader</code> is <code>null</code>.
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
    public int read(char[] chars, int off, int len)
            throws IOException {
        return reader.read(chars, off, len);
    }

    @Override
    public int read(CharBuffer buffer)
            throws IOException {
        return reader.read(buffer);
    }

    @Override
    public void close()
            throws IOException {
        reader.close();
    }

    @Override
    public Reader toReader() {
        return reader;
    }

}
