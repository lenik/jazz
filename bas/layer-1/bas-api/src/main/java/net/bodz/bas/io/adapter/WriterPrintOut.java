package net.bodz.bas.io.adapter;

import java.io.IOException;
import java.io.Writer;

import net.bodz.bas.io.IPrintOut;
import net.bodz.bas.io.PrintException;
import net.bodz.bas.meta.decl.NotNull;

public class WriterPrintOut
        extends Writer
        implements IPrintOut {

    private final Writer writer;

    /**
     * @throws NullPointerException If <code>writer</code> is <code>null</code>.
     */
    public WriterPrintOut(Writer writer) {
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
    public Writer toWriter() {
        return writer;
    }

    @Override
    public void flush() {
        try {
            writer.flush();
        } catch (IOException e) {
            throw new PrintException(e);
        }
    }

    @Override
    public void close()
            throws IOException {
        writer.close();
    }

}
