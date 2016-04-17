package net.bodz.bas.html.io.tag.gen;

import net.bodz.bas.html.io.HtmlDoc;
import net.bodz.bas.html.io.RecHtmlOut;

/**
 * Not all groups of links on a page need to be in a nav element — the element is primarily intended
 * for sections that consist of major navigation blocks. In particular, it is common for footers to
 * have a short list of links to various pages of a site, such as the terms of service, the home
 * page, and a copyright page. The footer element alone is sufficient for such cases; while a nav
 * element can be used in such cases, it is usually unnecessary.
 */
public class _HtmlNav<self_t extends RecHtmlOut<self_t>>
        extends RecHtmlOut<self_t> {

    public _HtmlNav(HtmlDoc doc) {
        super(doc);
    }

}
