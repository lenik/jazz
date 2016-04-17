package net.bodz.bas.html.io;

import net.bodz.bas.html.dom.HtmlDocType;
import net.bodz.bas.io.xml.XmlOutputFormat;

public class HtmlOutputFormat
        extends XmlOutputFormat {

    public HtmlDocType htmlDocType = HtmlDocType.HTML_5;

    public static final HtmlOutputFormat DEFAULT;
    static {
        DEFAULT = new HtmlOutputFormat();
    }

}
