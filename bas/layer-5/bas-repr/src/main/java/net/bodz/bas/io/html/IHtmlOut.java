package net.bodz.bas.io.html;

import net.bodz.bas.io.xml.IXmlOut;

public interface IHtmlOut
        extends IXmlOut {

    // IHtmlTagBuilder img();

    IHtmlOut NULL = new NullHtmlOut();

}
