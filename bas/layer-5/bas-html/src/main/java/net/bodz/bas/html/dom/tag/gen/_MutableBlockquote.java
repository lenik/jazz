package net.bodz.bas.html.dom.tag.gen;

import net.bodz.bas.html.dom.IHtmlTag;
import net.bodz.bas.html.dom.MutableHtmlTag;

/**
  * This example shows how a forum post could use blockquote to show what post a user is replying to. The article element is used for each post, to mark up the threading. 
  */
@SuppressWarnings("unchecked")
public class _MutableBlockquote<self_t extends IHtmlTag>
        extends MutableHtmlTag<self_t> {

    public _MutableBlockquote(IHtmlTag parent, String tagName) {
        super(parent, tagName);
    }

    public self_t cite(Object val) {
        attr("cite", val);
        return (self_t) this;
    }

}
