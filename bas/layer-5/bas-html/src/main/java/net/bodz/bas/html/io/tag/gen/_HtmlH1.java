package net.bodz.bas.html.io.tag.gen;

import net.bodz.bas.html.io.AbstractRecHtmlOut;
import net.bodz.bas.html.io.HtmlDoc;

/**
 * These elements have a rank given by the number in their name. The h1 element is said to have the
 * highest rank, the h6 element has the lowest rank, and two elements with the same name have equal
 * rank.
 */
public class _HtmlH1<self_t extends AbstractRecHtmlOut<self_t>>
        extends AbstractRecHtmlOut<self_t> {

    public _HtmlH1(HtmlDoc doc) {
        super(doc);
    }

}
