package net.bodz.bas.html.io.tag.gen;

import net.bodz.bas.html.io.HtmlDoc;
import net.bodz.bas.html.io.RecHtmlOut;

/**
 * The dt element itself, when used in a dl element, does not indicate that its contents are a term
 * being defined, but this can be indicated using the dfn element.
 */
public class _HtmlDt<self_t extends RecHtmlOut<self_t>>
        extends RecHtmlOut<self_t> {

    public _HtmlDt(HtmlDoc doc) {
        super(doc);
    }

}
