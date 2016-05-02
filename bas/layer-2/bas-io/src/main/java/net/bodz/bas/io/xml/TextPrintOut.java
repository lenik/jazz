package net.bodz.bas.io.xml;

import java.io.IOException;

import net.bodz.bas.io.AbstractPrintOut;

public class TextPrintOut
        extends AbstractPrintOut {

    private IXmlOut out;

    public TextPrintOut(IXmlOut out) {
        this.out = out;
    }

    @Override
    public void write(char[] cbuf, int off, int len)
            throws IOException {
        String str = new String(cbuf, off, len);
        write(str);
    }

    @Override
    public void write(String s)
            throws IOException {
        out.text(s);
    }

}
