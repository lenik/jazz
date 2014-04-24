package net.bodz.bas.io.html.tag;

import net.bodz.bas.io.html.DecoratedHtmlTagBuilder;
import net.bodz.bas.io.html.IHtmlTagBuilder;
import net.bodz.bas.io.xml.IXmlTagBuilder;

/**
  * The td element and its colspan, rowspan, and headers attributes take part in the table model. 
  */
@SuppressWarnings("unchecked")
class _HtmlTdBuilder<self_t extends IHtmlTagBuilder<?>>
        extends DecoratedHtmlTagBuilder<self_t> {

    public _HtmlTdBuilder(IXmlTagBuilder o) {
        super(o);
    }

    public self_t colspan(String val) {
        attr("colspan", val);
        return (self_t) this;
    }

    public self_t rowspan(String val) {
        attr("rowspan", val);
        return (self_t) this;
    }

    public self_t headers(String val) {
        attr("headers", val);
        return (self_t) this;
    }

}
