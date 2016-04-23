package net.bodz.bas.html.io.tag.gen;

import net.bodz.bas.html.io.HtmlDoc;
import net.bodz.bas.html.io.AbstractRecHtmlOut;

/**
 * The html element in the following example declares that the document's language is English.
 */
public class _HtmlHtml<self_t extends AbstractRecHtmlOut<self_t>>
        extends AbstractRecHtmlOut<self_t> {

    public _HtmlHtml(HtmlDoc doc) {
        super(doc);
    }

    /**
     * The manifest attribute only has an effect during the early stages of document load. Changing
     * the attribute dynamically thus has no effect (and thus, no DOM API is provided for this
     * attribute).
     */
    public self_t manifest(Object val) {
        return attr("manifest", val);
    }

}
