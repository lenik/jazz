package net.bodz.bas.io.html.tag;

import net.bodz.bas.io.html.DecoratedHtmlTagBuilder;
import net.bodz.bas.io.xml.IXmlTagBuilder;

/**
  * The col element and its span attribute take part in the table model. 
  */
public class HtmlColBuilder
        extends DecoratedHtmlTagBuilder<HtmlColBuilder> {

    public HtmlColBuilder(IXmlTagBuilder o) {
        super(o);
    }

    public HtmlColBuilder span(String val) {
        attr("span", val);
        return this;
    }

}
