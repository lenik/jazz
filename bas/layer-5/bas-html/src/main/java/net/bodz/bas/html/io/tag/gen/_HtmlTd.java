package net.bodz.bas.html.io.tag.gen;

import net.bodz.bas.html.io.HtmlDoc;
import net.bodz.bas.html.io.AbstractRecHtmlOut;

/**
 * The td element and its colspan, rowspan, and headers attributes take part in the table model.
 */
public class _HtmlTd<self_t extends AbstractRecHtmlOut<self_t>>
        extends AbstractRecHtmlOut<self_t> {

    public _HtmlTd(HtmlDoc doc) {
        super(doc);
    }

    public self_t colspan(Object val) {
        return attr("colspan", val);
    }

    public self_t rowspan(Object val) {
        return attr("rowspan", val);
    }

    public self_t headers(Object val) {
        return attr("headers", val);
    }

}
