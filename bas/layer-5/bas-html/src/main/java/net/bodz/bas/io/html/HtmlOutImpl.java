package net.bodz.bas.io.html;

import java.io.IOException;
import java.io.Writer;

import net.bodz.bas.io.ICharOut;
import net.bodz.bas.io.xml.XmlOutputFormat;

public class HtmlOutImpl {

    public static IHtmlOut from(ICharOut charOut) {
        return new HtmlOut_CharOut(charOut, new XmlOutputFormat());
    }

    public static IHtmlOut from(ICharOut charOut, XmlOutputFormat outputFormat) {
        return new HtmlOut_CharOut(charOut, outputFormat);
    }

    public static IHtmlOut from(Writer writer) {
        return new HtmlOut_Writer(writer, new XmlOutputFormat());
    }

    public static IHtmlOut from(Writer writer, XmlOutputFormat outputFormat) {
        return new HtmlOut_Writer(writer, outputFormat);
    }

}

class HtmlOut_CharOut
        extends AbstractHtmlOut {

    private ICharOut charOut;

    HtmlOut_CharOut(ICharOut charOut, XmlOutputFormat outputFormat) {
        super(outputFormat);
        if (charOut == null)
            throw new NullPointerException("charOut");
        this.charOut = charOut;
    }

    /** ⇱ Implementation Of {@link ICharOut}. */
    /* _____________________________ */static section.iface __CHAR__;

    @Override
    public void write(int c)
            throws IOException {
        charOut.write(c);
    }

    @Override
    public void write(char[] cbuf, int off, int len)
            throws IOException {
        charOut.write(cbuf, off, len);
    }

    @Override
    public void write(String s)
            throws IOException {
        charOut.write(s);
    }

    @Override
    public void write(String string, int off, int len)
            throws IOException {
        charOut.write(string, off, len);
    }

}

class HtmlOut_Writer
        extends AbstractHtmlOut {

    private Writer writer;

    HtmlOut_Writer(Writer writer, XmlOutputFormat outputFormat) {
        super(outputFormat);
        if (writer == null)
            throw new NullPointerException("writer");
        this.writer = writer;
    }

    /** ⇱ Implementation Of {@link ICharOut}. */
    /* _____________________________ */static section.iface __CHAR__;

    @Override
    public void write(int c)
            throws IOException {
        writer.write(c);
    }

    @Override
    public void write(char[] cbuf, int off, int len)
            throws IOException {
        writer.write(cbuf, off, len);
    }

    @Override
    public void write(String s)
            throws IOException {
        writer.write(s);
    }

    @Override
    public void write(String string, int off, int len)
            throws IOException {
        writer.write(string, off, len);
    }

}
