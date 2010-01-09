package net.bodz.bas.fs;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.Writer;
import java.nio.charset.Charset;

public abstract class AbstractWriteToolkit {

    private final IFile file;

    private boolean appendMode;
    private boolean autoFlush;

    public AbstractWriteToolkit(IFile file) {
        if (file == null)
            throw new NullPointerException("file");
        this.file = file;
    }

    public Charset getCharset() {
        return file.getCharset();
    }

    public boolean getAppendMode() {
        return appendMode;
    }

    public void setAppendMode(boolean appendMode) {
        this.appendMode = appendMode;
    }

    public boolean isAutoFlush() {
        return autoFlush;
    }

    public void setAutoFlush(boolean autoFlush) {
        this.autoFlush = autoFlush;
    }

    public abstract OutputStream newOutputStream()
            throws IOException;

    public PrintStream newPrintStream()
            throws IOException {
        OutputStream out = newOutputStream();
        return new PrintStream(out, autoFlush, getCharset().name());
    }

    public Writer newWriter()
            throws IOException {
        OutputStream out = newOutputStream();
        return new OutputStreamWriter(out, getCharset());
    }

    public void writeBytes(byte[] bytes, int off, int len)
            throws IOException {
        if (bytes == null)
            throw new NullPointerException("bytes");
        OutputStream out = newOutputStream();
        try {
            out.write(bytes, off, len);
        } finally {
            out.close();
        }
    }

    public void writeBytes(byte[] bytes)
            throws IOException {
        if (bytes == null)
            throw new NullPointerException("bytes");
        writeBytes(bytes, 0, bytes.length);
    }

    public void writeString(String string)
            throws IOException {
        byte[] bytes = string.getBytes(getCharset());
        writeBytes(bytes);
    }

}
