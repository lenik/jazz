package net.bodz.bas.io.html.tag;

import net.bodz.bas.io.html.DecoratedHtmlTagBuilder;
import net.bodz.bas.io.xml.IXmlTagBuilder;

/**
  * The colgroup element and its span attribute take part in the table model. 
  */
public class HtmlColgroupBuilder
        extends DecoratedHtmlTagBuilder<HtmlColgroupBuilder> {

    public HtmlColgroupBuilder(IXmlTagBuilder o) {
        super(o);
    }

    public HtmlColgroupBuilder span(String val) {
        attr("span", val);
        return this;
    }

}
