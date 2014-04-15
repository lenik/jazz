package net.bodz.bas.io.html.tag;

import net.bodz.bas.io.html.DecoratedHtmlTagBuilder;
import net.bodz.bas.io.xml.IXmlTagBuilder;

/**
  * The td element and its colspan, rowspan, and headers attributes take part in the table model. 
  */
public class HtmlTdBuilder
        extends DecoratedHtmlTagBuilder<HtmlTdBuilder> {

    public HtmlTdBuilder(IXmlTagBuilder o) {
        super(o);
    }

    public HtmlTdBuilder colspan(String val) {
        attr("colspan", val);
        return this;
    }

    public HtmlTdBuilder rowspan(String val) {
        attr("rowspan", val);
        return this;
    }

    public HtmlTdBuilder headers(String val) {
        attr("headers", val);
        return this;
    }

}
