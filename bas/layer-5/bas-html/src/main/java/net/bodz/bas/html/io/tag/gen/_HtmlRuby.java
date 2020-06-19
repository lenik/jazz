package net.bodz.bas.html.io.tag.gen;

import net.bodz.bas.html.io.AbstractRecHtmlOut;
import net.bodz.bas.html.io.HtmlDoc;

/**
 * The ruby, rb, rtc, and rt elements can be used for a variety of kinds of annotations, including
 * in particular (though by no means limited to) those described below. For more details on Japanese
 * Ruby in particular, and how to render Ruby for Japanese, see Requirements for Japanese Text
 * Layout. [JLREQ] The rp element can be used as fallback content when ruby rendering is not
 * supported.
 */
public class _HtmlRuby<self_t extends AbstractRecHtmlOut<self_t>>
        extends AbstractRecHtmlOut<self_t> {

    public _HtmlRuby(HtmlDoc doc) {
        super(doc);
    }

}
