package net.bodz.bas.html.io.tag.gen;

import net.bodz.bas.html.io.HtmlDoc;
import net.bodz.bas.html.io.RecHtmlOut;

/**
 * The header element is not sectioning content; it doesn't introduce a new section.
 */
public class _HtmlHeader<self_t extends RecHtmlOut<self_t>>
        extends RecHtmlOut<self_t> {

    public _HtmlHeader(HtmlDoc doc) {
        super(doc);
    }

}