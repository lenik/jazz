package net.bodz.bas.io.xml;

import net.bodz.bas.io.IPrintOut;
import net.bodz.bas.meta.decl.NotNull;

public class TextPrintOut
        implements IPrintOut {

    private final IXmlOut out;

    public TextPrintOut(IXmlOut out) {
        this.out = out;
    }

    @Override
    public void write(int ch) {
        out.text(String.valueOf((char) ch));
    }

    @Override
    public void write(@NotNull char[] buf, int off, int len) {
        out.text(new String(buf, off, len));
    }

    @Override
    public void write(@NotNull String s) {
        out.text(s);
    }

    @Override
    public void flush() {
    }

    @Override
    public void close() {
    }

}
