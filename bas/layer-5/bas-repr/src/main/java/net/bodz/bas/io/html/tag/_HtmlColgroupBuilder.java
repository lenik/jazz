package net.bodz.bas.io.html.tag;

import net.bodz.bas.io.html.DecoratedHtmlTagBuilder;
import net.bodz.bas.io.html.IHtmlTagBuilder;
import net.bodz.bas.io.xml.IXmlTagBuilder;

/**
  * The colgroup element and its span attribute take part in the table model. 
  */
@SuppressWarnings("unchecked")
class _HtmlColgroupBuilder<self_t extends IHtmlTagBuilder<?>>
        extends DecoratedHtmlTagBuilder<self_t> {

    public _HtmlColgroupBuilder(IXmlTagBuilder o) {
        super(o);
    }

    public self_t span(String val) {
        attr("span", val);
        return (self_t) this;
    }

}
