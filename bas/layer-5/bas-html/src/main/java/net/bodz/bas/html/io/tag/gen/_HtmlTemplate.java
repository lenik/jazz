package net.bodz.bas.html.io.tag.gen;

import net.bodz.bas.html.io.AbstractRecHtmlOut;
import net.bodz.bas.html.io.HtmlDoc;

/**
 * The template element allows for the declaration of document fragments which are unused by the
 * document when loaded, but are parsed as HTML and are available at runtime for use by the web
 * page.
 */
public class _HtmlTemplate<self_t extends AbstractRecHtmlOut<self_t>>
        extends AbstractRecHtmlOut<self_t> {

    public _HtmlTemplate(HtmlDoc doc) {
        super(doc);
    }

}
