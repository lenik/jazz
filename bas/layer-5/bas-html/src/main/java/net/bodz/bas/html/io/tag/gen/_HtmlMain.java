package net.bodz.bas.html.io.tag.gen;

import net.bodz.bas.html.io.AbstractRecHtmlOut;
import net.bodz.bas.html.io.HtmlDoc;

/**
 * The main element is not suitable for use to identify the main content areas of sub sections of a
 * document or application. The simplest solution is to not mark up the main content of a sub
 * section at all, and just leave it as implicit, but an author could use a grouping content or
 * sectioning content element as appropriate.
 */
public class _HtmlMain<self_t extends AbstractRecHtmlOut<self_t>>
        extends AbstractRecHtmlOut<self_t> {

    public _HtmlMain(HtmlDoc doc) {
        super(doc);
    }

}
