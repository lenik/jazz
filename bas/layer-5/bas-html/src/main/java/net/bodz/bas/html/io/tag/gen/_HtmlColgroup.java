package net.bodz.bas.html.io.tag.gen;

import net.bodz.bas.html.io.AbstractRecHtmlOut;
import net.bodz.bas.html.io.HtmlDoc;

/**
 * The colgroup element and its span attribute take part in the table model.
 */
public class _HtmlColgroup<self_t extends AbstractRecHtmlOut<self_t>>
        extends AbstractRecHtmlOut<self_t> {

    public _HtmlColgroup(HtmlDoc doc) {
        super(doc);
    }

    public self_t span(Object val) {
        return attr("span", val);
    }

}
