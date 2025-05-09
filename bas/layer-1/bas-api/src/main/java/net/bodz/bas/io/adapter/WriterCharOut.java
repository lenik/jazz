package net.bodz.bas.io.adapter;

import java.io.IOException;
import java.io.Writer;

import net.bodz.bas.io.ICharOut;
import net.bodz.bas.meta.decl.NotNull;

public class WriterCharOut
        extends Writer
        implements ICharOut {

    private final Writer writer;

    /**
     * @throws NullPointerException If <code>writer</code> is <code>null</code>.
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
    public void writeChar(int ch)
            throws IOException {
        writer.write(ch);
    }

    @Override
    public void write(@NotNull char[] chars)
            throws IOException {
        writer.write(chars);
    }

    @Override
    public void write(@NotNull char[] chars, int off, int len)
            throws IOException {
        writer.write(chars, off, len);
    }

    @Override
    public void write(@NotNull String s)
            throws IOException {
        writer.write(s);
    }

    @Override
    public void write(@NotNull String string, int off, int len)
            throws IOException {
        writer.write(string, off, len);
    }

    @Override
    public void flush()
            throws IOException {
        writer.flush();
    }

    @Override
    public void close()
            throws IOException {
        writer.close();
    }

    @Override
    public Writer toWriter() {
        return writer;
    }

}
