package net.bodz.bas.html.io.tag;

import net.bodz.bas.html.io.HtmlDoc;
import net.bodz.bas.html.io.tag.gen._HtmlTr;

public class HtmlTr
        extends _HtmlTr<HtmlTr> {

    public HtmlTr(HtmlDoc doc) {
        super(doc);
    }

    public HtmlTr valign(Object val) {
        if (val == null)
            throw new NullPointerException("val");
        attr("valign", val);
        return this;
    }

}
