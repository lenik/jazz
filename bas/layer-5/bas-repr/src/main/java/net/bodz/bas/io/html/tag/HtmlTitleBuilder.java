package net.bodz.bas.io.html.tag;

import net.bodz.bas.io.html.DecoratedHtmlTagBuilder;
import net.bodz.bas.io.xml.IXmlTagBuilder;

/**
  * <!--TOPIC:HTML-->The title element represents the document's title or name. Authors should use titles that identify their documents even when they are used out of context, for example in a user's history or bookmarks, or in search results. The document's title is often different from its first heading, since the first heading does not have to stand alone when taken out of context. 
  */
public class HtmlTitleBuilder
        extends DecoratedHtmlTagBuilder<HtmlTitleBuilder> {

    public HtmlTitleBuilder(IXmlTagBuilder o) {
        super(o);
    }

}
