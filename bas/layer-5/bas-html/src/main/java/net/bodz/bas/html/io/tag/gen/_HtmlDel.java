package net.bodz.bas.html.io.tag.gen;

import net.bodz.bas.html.io.AbstractRecHtmlOut;
import net.bodz.bas.html.io.HtmlDoc;

/**
 * <!--TOPIC:HTML-->The del element represents a removal from the document.
 */
public class _HtmlDel<self_t extends AbstractRecHtmlOut<self_t>>
        extends AbstractRecHtmlOut<self_t> {

    public _HtmlDel(HtmlDoc doc) {
        super(doc);
    }

    public self_t cite(Object val) {
        return attr("cite", val);
    }

    public self_t datetime(Object val) {
        return attr("datetime", val);
    }

}
