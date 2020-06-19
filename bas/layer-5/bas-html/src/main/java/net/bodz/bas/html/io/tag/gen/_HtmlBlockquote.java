package net.bodz.bas.html.io.tag.gen;

import net.bodz.bas.html.io.AbstractRecHtmlOut;
import net.bodz.bas.html.io.HtmlDoc;

/**
 * This example shows how a forum post could use blockquote to show what post a user is replying to.
 * The article element is used for each post, to mark up the threading.
 */
public class _HtmlBlockquote<self_t extends AbstractRecHtmlOut<self_t>>
        extends AbstractRecHtmlOut<self_t> {

    public _HtmlBlockquote(HtmlDoc doc) {
        super(doc);
    }

    public self_t cite(Object val) {
        return attr("cite", val);
    }

}
