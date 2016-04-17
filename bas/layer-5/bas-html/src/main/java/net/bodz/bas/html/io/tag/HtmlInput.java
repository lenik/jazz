package net.bodz.bas.html.io.tag;

import net.bodz.bas.html.io.HtmlDoc;
import net.bodz.bas.html.io.tag.gen._HtmlInput;

public class HtmlInput
        extends _HtmlInput<HtmlInput> {

    public HtmlInput(HtmlDoc doc) {
        super(doc);
    }

    public HtmlInput acceptCamera(Object val) {
        if (val == null)
            throw new NullPointerException("val");
        attr("accept-camera", val);
        return this;
    }

}
