package net.bodz.bas.io.html.tag;

import net.bodz.bas.io.html.DecoratedHtmlTagBuilder;
import net.bodz.bas.io.xml.IXmlTagBuilder;

/**
  * <!--TOPIC:HTML-->The ul element represents a list of items, where the order of the items is not important — that is, where changing the order would not materially change the meaning of the document. 
  */
public class HtmlUlBuilder
        extends DecoratedHtmlTagBuilder<HtmlUlBuilder> {

    public HtmlUlBuilder(IXmlTagBuilder o) {
        super(o);
    }

}
