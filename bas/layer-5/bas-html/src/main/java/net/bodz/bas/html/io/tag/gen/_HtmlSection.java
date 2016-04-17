package net.bodz.bas.html.io.tag.gen;

import net.bodz.bas.html.io.HtmlDoc;
import net.bodz.bas.html.io.RecHtmlOut;

/**
 * The section element is not a generic container element. When an element is needed only for
 * styling purposes or as a convenience for scripting, authors are encouraged to use the div element
 * instead. A general rule is that the section element is appropriate only if the element's contents
 * would be listed explicitly in the document's outline.
 */
public class _HtmlSection<self_t extends RecHtmlOut<self_t>>
        extends RecHtmlOut<self_t> {

    public _HtmlSection(HtmlDoc doc) {
        super(doc);
    }

}
