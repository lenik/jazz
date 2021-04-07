package net.bodz.bas.fmt.xml;

import java.io.IOException;
import java.io.Writer;
import java.nio.CharBuffer;

import net.bodz.bas.t.model.IWrapper;

public class AppendableWriter
        extends Writer
        implements
            IWrapper<Appendable> {

    Appendable _orig;

    public AppendableWriter(Appendable _orig) {
        this._orig = _orig;
    }

    @Override
    public Appendable getWrapped() {
        return _orig;
    }

    @Override
    public AppendableWriter append(CharSequence csq)
            throws IOException {
        _orig.append(csq);
        return this;
    }

    @Override
    public AppendableWriter append(CharSequence csq, int start, int end)
            throws IOException {
        _orig.append(csq, start, end);
        return this;
    }

    @Override
    public AppendableWriter append(char c)
            throws IOException {
        _orig.append(c);
        return this;
    }

    @Override
    public void write(int c)
            throws IOException {
        append((char) c);
    }

    @Override
    public void write(char[] cbuf, int off, int len)
            throws IOException {
        CharSequence charSeq = CharBuffer.wrap(cbuf, off, len);
        append(charSeq);
    }

    @Override
    public void write(String str)
            throws IOException {
        append(str);
    }

    @Override
    public void write(String str, int off, int len)
            throws IOException {
        append(str, off, off + len);
    }

    @Override
    public void flush()
            throws IOException {
    }

    @Override
    public void close()
            throws IOException {
    }

}
