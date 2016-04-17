package net.bodz.bas.html.io.tag.gen;

import net.bodz.bas.html.io.HtmlDoc;
import net.bodz.bas.html.io.RecHtmlOut;

/**
 * The b element should be used as a last resort when no other element is more appropriate. In
 * particular, headings should use the h1 to h6 elements, stress emphasis should use the em element,
 * importance should be denoted with the strong element, and text marked or highlighted should use
 * the mark element.
 */
public class _HtmlB<self_t extends RecHtmlOut<self_t>>
        extends RecHtmlOut<self_t> {

    public _HtmlB(HtmlDoc doc) {
        super(doc);
    }

}
