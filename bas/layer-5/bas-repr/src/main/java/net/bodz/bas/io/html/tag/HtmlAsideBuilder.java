package net.bodz.bas.io.html.tag;

import net.bodz.bas.io.html.DecoratedHtmlTagBuilder;
import net.bodz.bas.io.xml.IXmlTagBuilder;

/**
  * <!--TOPIC:HTML-->The aside element represents a section of a page that consists of content that is tangentially related to the content around the aside element, and which could be considered separate from that content. Such sections are often represented as sidebars in printed typography. 
  */
public class HtmlAsideBuilder
        extends DecoratedHtmlTagBuilder<HtmlAsideBuilder> {

    public HtmlAsideBuilder(IXmlTagBuilder o) {
        super(o);
    }

}
