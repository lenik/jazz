package net.bodz.bas.io.html.tag;

import net.bodz.bas.io.html.DecoratedHtmlTagBuilder;
import net.bodz.bas.io.xml.IXmlTagBuilder;

/**
  * This example shows how a forum post could use blockquote to show what post a user is replying to. The article element is used for each post, to mark up the threading. 
  */
public class HtmlBlockquoteBuilder
        extends DecoratedHtmlTagBuilder<HtmlBlockquoteBuilder> {

    public HtmlBlockquoteBuilder(IXmlTagBuilder o) {
        super(o);
    }

    public HtmlBlockquoteBuilder cite(String val) {
        attr("cite", val);
        return this;
    }

}
