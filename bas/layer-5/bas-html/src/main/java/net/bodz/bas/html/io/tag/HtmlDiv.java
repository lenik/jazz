package net.bodz.bas.html.io.tag;

import net.bodz.bas.html.io.HtmlDoc;
import net.bodz.bas.html.io.tag.gen._HtmlDiv;

public class HtmlDiv
        extends _HtmlDiv<HtmlDiv> {

    public HtmlDiv(HtmlDoc doc) {
        super(doc);
    }

    public HtmlDiv align(Object val) {
        if (val == null)
            throw new NullPointerException("val");
        attr("align", val);
        return this;
    }

}
