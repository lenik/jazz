package net.bodz.bas.html.io.tag.gen;

import net.bodz.bas.html.io.HtmlDoc;
import net.bodz.bas.html.io.RecHtmlOut;

/**
 * The title element is a required child in most situations, but when a higher-level protocol
 * provides title information, e.g. in the Subject line of an e-mail when HTML is used as an e-mail
 * authoring format, the title element can be omitted.
 */
public class _HtmlHead<self_t extends RecHtmlOut<self_t>>
        extends RecHtmlOut<self_t> {

    public _HtmlHead(HtmlDoc doc) {
        super(doc);
    }

}
