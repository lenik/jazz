package net.bodz.bas.html.io.tag.gen;

import net.bodz.bas.html.io.HtmlDoc;
import net.bodz.bas.html.io.AbstractRecHtmlOut;

/**
 * In this example, the heading is really "Flowers, Bees, and Honey", but the author has added a
 * light-hearted addition to the heading. The strong element is thus used to mark up the first part
 * to distinguish it from the latter part.
 */
public class _HtmlStrong<self_t extends AbstractRecHtmlOut<self_t>>
        extends AbstractRecHtmlOut<self_t> {

    public _HtmlStrong(HtmlDoc doc) {
        super(doc);
    }

}
