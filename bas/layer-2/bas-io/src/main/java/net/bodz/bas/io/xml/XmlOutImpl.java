package net.bodz.bas.io.xml;

import java.io.IOException;

import net.bodz.bas.io.ICharOut;
import net.bodz.bas.io.IPrintOut;
import net.bodz.bas.io.impl.PrintOutImpl;

public class XmlOutImpl
        extends AbstractXmlOut {

    private IPrintOut printOut;

    public XmlOutImpl() {
        super();
    }

    protected XmlOutImpl(IPrintOut printOut) {
        this(printOut, new XmlOutputFormat());
    }

    /**
     * Print out.
     * 
     * @throws NullPointerException
     *             If any argument is <code>null</code>.
     */
    public XmlOutImpl(IPrintOut printOut, XmlOutputFormat outputFormat) {
        super(outputFormat);
        if (printOut == null)
            throw new NullPointerException("printOut");
        this.printOut = printOut;
    }

    public static IXmlOut from(IPrintOut printOut) {
        if (printOut instanceof IXmlOut)
            return (IXmlOut) printOut;
        else
            return new XmlOutImpl((IPrintOut) printOut);
    }

    public static IXmlOut from(ICharOut charOut) {
        if (charOut instanceof IXmlOut)
            return ((IXmlOut) charOut);
        else
            return new XmlOutImpl(PrintOutImpl.from(charOut));
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
