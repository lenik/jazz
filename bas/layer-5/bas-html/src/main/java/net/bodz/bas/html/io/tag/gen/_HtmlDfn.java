package net.bodz.bas.html.io.tag.gen;

import net.bodz.bas.html.io.HtmlDoc;
import net.bodz.bas.html.io.RecHtmlOut;

/**
 * <!--TOPIC:HTML-->The dfn element represents the defining instance of a term. The paragraph,
 * description list group, or section that is the nearest ancestor of the dfn element must also
 * contain the definition(s) for the term given by the dfn element.
 */
public class _HtmlDfn<self_t extends RecHtmlOut<self_t>>
        extends RecHtmlOut<self_t> {

    public _HtmlDfn(HtmlDoc doc) {
        super(doc);
    }

}
