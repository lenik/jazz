package net.bodz.bas.html.io.tag.gen;

import net.bodz.bas.html.io.HtmlDoc;
import net.bodz.bas.html.io.RecHtmlOut;

/**
 * The footer element is not sectioning content; it doesn't introduce a new section.
 */
public class _HtmlFooter<self_t extends RecHtmlOut<self_t>>
        extends RecHtmlOut<self_t> {

    public _HtmlFooter(HtmlDoc doc) {
        super(doc);
    }

}
