package net.bodz.bas.html.io.tag.gen;

import net.bodz.bas.html.io.HtmlDoc;
import net.bodz.bas.html.io.AbstractRecHtmlOut;

/**
 * The noscript element is a blunt instrument. Sometimes, scripts might be enabled, but for some
 * reason the page's script might fail. For this reason, it's generally better to avoid using
 * noscript, and to instead design the script to change the page from being a scriptless page to a
 * scripted page on the fly, as in the next example:
 */
public class _HtmlNoscript<self_t extends AbstractRecHtmlOut<self_t>>
        extends AbstractRecHtmlOut<self_t> {

    public _HtmlNoscript(HtmlDoc doc) {
        super(doc);
    }

}
