package net.bodz.bas.html.io.tag.gen;

import net.bodz.bas.html.io.AbstractRecHtmlOut;
import net.bodz.bas.html.io.HtmlDoc;

/**
 * The header element is not sectioning content; it doesn't introduce a new section.
 */
public class _HtmlHeader<self_t extends AbstractRecHtmlOut<self_t>>
        extends AbstractRecHtmlOut<self_t> {

    public _HtmlHeader(HtmlDoc doc) {
        super(doc);
    }

}
