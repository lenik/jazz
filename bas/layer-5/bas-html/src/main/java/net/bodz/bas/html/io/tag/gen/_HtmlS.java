package net.bodz.bas.html.io.tag.gen;

import net.bodz.bas.html.io.HtmlDoc;
import net.bodz.bas.html.io.RecHtmlOut;

/**
 * The s element is not appropriate when indicating document edits; to mark a span of text as having
 * been removed from a document, use the del element.
 */
public class _HtmlS<self_t extends RecHtmlOut<self_t>>
        extends RecHtmlOut<self_t> {

    public _HtmlS(HtmlDoc doc) {
        super(doc);
    }

}
