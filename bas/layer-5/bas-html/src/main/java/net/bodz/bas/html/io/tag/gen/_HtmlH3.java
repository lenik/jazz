package net.bodz.bas.html.io.tag.gen;

import net.bodz.bas.html.io.HtmlDoc;
import net.bodz.bas.html.io.AbstractRecHtmlOut;

/**
 * These elements have a rank given by the number in their name. The h1 element is said to have the
 * highest rank, the h6 element has the lowest rank, and two elements with the same name have equal
 * rank.
 */
public class _HtmlH3<self_t extends AbstractRecHtmlOut<self_t>>
        extends AbstractRecHtmlOut<self_t> {

    public _HtmlH3(HtmlDoc doc) {
        super(doc);
    }

}
