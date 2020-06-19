package net.bodz.bas.html.io.tag.gen;

import net.bodz.bas.html.io.AbstractRecHtmlOut;
import net.bodz.bas.html.io.HtmlDoc;

/**
 * The footer element is not sectioning content; it doesn't introduce a new section.
 */
public class _HtmlFooter<self_t extends AbstractRecHtmlOut<self_t>>
        extends AbstractRecHtmlOut<self_t> {

    public _HtmlFooter(HtmlDoc doc) {
        super(doc);
    }

}
