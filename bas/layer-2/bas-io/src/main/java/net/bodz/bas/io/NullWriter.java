package net.bodz.bas.io;

import java.io.IOException;
import java.io.Writer;

public class NullWriter
        extends Writer {

    @Override
    public void write(int c)
            throws IOException {
    }

    @Override
    public void write(char[] cbuf, int off, int len)
            throws IOException {
    }

    @Override
    public void write(char[] cbuf)
            throws IOException {
    }

    @Override
    public void write(String str, int off, int len)
            throws IOException {
    }

    @Override
    public void write(String str)
            throws IOException {
    }

    @Override
    public void close()
            throws IOException {
    }

    @Override
    public void flush()
            throws IOException {
    }

    static NullWriter nil = new NullWriter();

    public static NullWriter getInstance() {
        return nil;
    }

}
