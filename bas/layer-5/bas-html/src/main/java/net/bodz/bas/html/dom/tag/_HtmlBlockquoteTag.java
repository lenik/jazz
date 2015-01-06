package net.bodz.bas.html.dom.tag;

import net.bodz.bas.html.dom.AbstractHtmlTag;
import net.bodz.bas.html.dom.IHtmlTag;

/**
  * This example shows how a forum post could use blockquote to show what post a user is replying to. The article element is used for each post, to mark up the threading. 
  */
@SuppressWarnings("unchecked")
class _HtmlBlockquoteTag<self_t extends IHtmlTag>
        extends AbstractHtmlTag<self_t> {

    public _HtmlBlockquoteTag(IHtmlTag parent, String tagName) {
        super(parent, tagName);
    }

    public self_t cite(Object val) {
        attr("cite", val);
        return (self_t) this;
    }

}
