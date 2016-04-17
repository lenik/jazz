package net.bodz.bas.html.io.tag;

import net.bodz.bas.html.io.HtmlDoc;
import net.bodz.bas.html.io.tag.gen._HtmlA;

public class HtmlA
        extends _HtmlA<HtmlA> {

    public HtmlA(HtmlDoc doc) {
        super(doc);
    }

    public HtmlA href(String target, Object val) {
        super.href(val);
        target(target);
        return this;
    }

}
