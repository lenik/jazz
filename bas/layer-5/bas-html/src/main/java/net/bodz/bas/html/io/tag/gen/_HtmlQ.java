package net.bodz.bas.html.io.tag.gen;

import net.bodz.bas.html.io.HtmlDoc;
import net.bodz.bas.html.io.RecHtmlOut;

/**
 * The q element must not be used in place of quotation marks that do not represent quotes; for
 * example, it is inappropriate to use the q element for marking up sarcastic statements.
 */
public class _HtmlQ<self_t extends RecHtmlOut<self_t>>
        extends RecHtmlOut<self_t> {

    public _HtmlQ(HtmlDoc doc) {
        super(doc);
    }

    public self_t cite(Object val) {
        return attr("cite", val);
    }

}
