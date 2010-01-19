package net.bodz.bas.sio;

import java.io.IOException;
import java.io.Writer;

public class WriterCharOut
        extends CharOut {

    private final Writer writer;

    /**
     * @throws NullPointerException
     *             If <code>writer</code> is <code>null</code>.
     */
    public WriterCharOut(Writer writer) {
        if (writer == null)
            throw new NullPointerException("writer");
        this.writer = writer;
    }

    @Override
    public void write(int c)
            throws IOException {
        writer.write(c);
    }

    @Override
    public void write(char[] chars, int off, int len)
            throws IOException {
        writer.write(chars, off, len);
    }

    @Override
    public void write(String string, int off, int len)
            throws IOException {
        writer.write(string, off, len);
    }

    @Override
    public void flush(boolean strict)
            throws IOException {
        writer.flush();
    }

}
