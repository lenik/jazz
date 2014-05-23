package net.bodz.bas.html.dom.tag;

import net.bodz.bas.html.dom.AbstractHtmlTag;
import net.bodz.bas.html.dom.IHtmlTag;

/**
  * <!--TOPIC:HTML-->The title element represents the document's title or name. Authors should use titles that identify their documents even when they are used out of context, for example in a user's history or bookmarks, or in search results. The document's title is often different from its first heading, since the first heading does not have to stand alone when taken out of context. 
  */
class _HtmlTitleTag<self_t extends IHtmlTag>
        extends AbstractHtmlTag<self_t> {

    public _HtmlTitleTag(IHtmlTag parent, String tagName) {
        super(parent, tagName);
    }

}
