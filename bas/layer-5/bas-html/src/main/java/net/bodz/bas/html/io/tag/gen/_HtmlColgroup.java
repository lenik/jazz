package net.bodz.bas.html.io.tag.gen;

import net.bodz.bas.html.io.HtmlDoc;
import net.bodz.bas.html.io.RecHtmlOut;

/**
 * The colgroup element and its span attribute take part in the table model.
 */
public class _HtmlColgroup<self_t extends RecHtmlOut<self_t>>
        extends RecHtmlOut<self_t> {

    public _HtmlColgroup(HtmlDoc doc) {
        super(doc);
    }

    public self_t span(Object val) {
        return attr("span", val);
    }

}
