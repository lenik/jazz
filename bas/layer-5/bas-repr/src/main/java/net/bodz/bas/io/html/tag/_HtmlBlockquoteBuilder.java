package net.bodz.bas.io.html.tag;

import net.bodz.bas.io.html.DecoratedHtmlTagBuilder;
import net.bodz.bas.io.html.IHtmlTagBuilder;
import net.bodz.bas.io.xml.IXmlTagBuilder;

/**
  * This example shows how a forum post could use blockquote to show what post a user is replying to. The article element is used for each post, to mark up the threading. 
  */
@SuppressWarnings("unchecked")
class _HtmlBlockquoteBuilder<self_t extends IHtmlTagBuilder<?>>
        extends DecoratedHtmlTagBuilder<self_t> {

    public _HtmlBlockquoteBuilder(IXmlTagBuilder o) {
        super(o);
    }

    public self_t cite(String val) {
        attr("cite", val);
        return (self_t) this;
    }

}
