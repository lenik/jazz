package net.bodz.bas.flow.units.builtin.sinks;

import java.io.IOException;
import java.io.Writer;

public class WriterSinkUnit extends TextProcessSinkUnit {

    private Writer out;
    private boolean autoFlush;

    public WriterSinkUnit(Writer out, boolean autoFlush) throws IOException {
        this.out = out;
        this.autoFlush = autoFlush;
    }

    public WriterSinkUnit(Writer out) throws IOException {
        this(out, false);
    }

    public Writer getWriter() {
        return out;
    }

    public void setWriter(Writer out) {
        this.out = out;
    }

    public boolean isAutoFlush() {
        return autoFlush;
    }

    public void setAutoFlush(boolean autoFlush) {
        this.autoFlush = autoFlush;
    }

    @Override
    public void reset() throws IOException {
    }

    @Override
    public void flush() throws IOException {
        out.flush();
    }

    @Override
    public void recv(char[] chars, int start, int end) throws IOException {
        int length = end - start;
        out.write(chars, start, length);
        if (autoFlush)
            out.flush();
    }

}
