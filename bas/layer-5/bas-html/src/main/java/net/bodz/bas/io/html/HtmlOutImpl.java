package net.bodz.bas.io.html;

import java.io.IOException;

import net.bodz.bas.io.ICharOut;
import net.bodz.bas.io.IPrintOut;
import net.bodz.bas.io.impl.PrintOutImpl;
import net.bodz.bas.io.xml.XmlOutputFormat;

public class HtmlOutImpl
        extends AbstractHtmlOut {

    private IPrintOut printOut;

    public HtmlOutImpl() {
        super();
    }

    protected HtmlOutImpl(IPrintOut printOut) {
        this(printOut, new XmlOutputFormat());
    }

    /**
     * Print out.
     * 
     * @throws NullPointerException
     *             If any argument is <code>null</code>.
     */
    public HtmlOutImpl(IPrintOut printOut, XmlOutputFormat outputFormat) {
        super(outputFormat);
        if (printOut == null)
            throw new NullPointerException("printOut");
        this.printOut = printOut;
    }

    public static IHtmlOut from(IPrintOut printOut) {
        if (printOut instanceof IHtmlOut)
            return (IHtmlOut) printOut;
        else
            return new HtmlOutImpl((IPrintOut) printOut);
    }

    public static IHtmlOut from(ICharOut charOut) {
        if (charOut instanceof IHtmlOut)
            return ((IHtmlOut) charOut);
        else
            return new HtmlOutImpl(PrintOutImpl.from(charOut));
    }

    /** ⇱ Implementation Of {@link ICharOut}. */
    /* _____________________________ */static section.iface __CHAR__;

    @Override
    public void write(int c)
            throws IOException {
        printOut.write(c);
    }

    @Override
    public void write(char[] cbuf, int off, int len)
            throws IOException {
        printOut.write(cbuf, off, len);
    }

}
