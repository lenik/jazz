package net.bodz.bas.html.io.tag.gen;

import net.bodz.bas.html.io.AbstractRecHtmlOut;
import net.bodz.bas.html.io.HtmlDoc;

/**
 * <!--TOPIC:HTML-->The title element represents the document's title or name. Authors should use
 * titles that identify their documents even when they are used out of context, for example in a
 * user's history or bookmarks, or in search results. The document's title is often different from
 * its first heading, since the first heading does not have to stand alone when taken out of
 * context.
 */
public class _HtmlTitle<self_t extends AbstractRecHtmlOut<self_t>>
        extends AbstractRecHtmlOut<self_t> {

    public _HtmlTitle(HtmlDoc doc) {
        super(doc);
    }

}
