package net.bodz.bas.html.io.tag;

import net.bodz.bas.html.io.HtmlDoc;
import net.bodz.bas.html.io.tag.gen._HtmlTd;

public class HtmlTd
        extends _HtmlTd<HtmlTd> {

    public HtmlTd(HtmlDoc doc) {
        super(doc);
    }

    public HtmlTd align(Object val) {
        if (val == null)
            throw new NullPointerException("val");
        attr("align", val);
        return this;
    }

    public HtmlTd valign(Object val) {
        if (val == null)
            throw new NullPointerException("val");
        attr("valign", val);
        return this;
    }

    public HtmlTd width(Object val) {
        if (val == null)
            throw new NullPointerException("val");
        attr("width", val);
        return this;
    }

}
