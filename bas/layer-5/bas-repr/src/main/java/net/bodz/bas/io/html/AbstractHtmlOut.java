package net.bodz.bas.io.html;

import net.bodz.bas.io.xml.AbstractXmlOut;
import net.bodz.bas.io.xml.XmlOutputFormat;

public abstract class AbstractHtmlOut
        extends AbstractXmlOut
        implements IHtmlOut {

    public AbstractHtmlOut() {
        super();
    }

    public AbstractHtmlOut(XmlOutputFormat outputFormat) {
        super(outputFormat);
    }

}
