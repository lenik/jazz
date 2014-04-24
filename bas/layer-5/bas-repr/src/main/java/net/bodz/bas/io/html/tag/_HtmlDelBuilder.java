package net.bodz.bas.io.html.tag;

import net.bodz.bas.io.html.DecoratedHtmlTagBuilder;
import net.bodz.bas.io.html.IHtmlTagBuilder;
import net.bodz.bas.io.xml.IXmlTagBuilder;

/**
  * <!--TOPIC:HTML-->The del element represents a removal from the document. 
  */
@SuppressWarnings("unchecked")
class _HtmlDelBuilder<self_t extends IHtmlTagBuilder<?>>
        extends DecoratedHtmlTagBuilder<self_t> {

    public _HtmlDelBuilder(IXmlTagBuilder o) {
        super(o);
    }

    public self_t cite(String val) {
        attr("cite", val);
        return (self_t) this;
    }

    public self_t datetime(String val) {
        attr("datetime", val);
        return (self_t) this;
    }

}
