package net.bodz.bas.io.html.tag;

import net.bodz.bas.io.html.DecoratedHtmlTagBuilder;
import net.bodz.bas.io.html.IHtmlTagBuilder;
import net.bodz.bas.io.xml.IXmlTagBuilder;

/**
  * <!--TOPIC:HTML-->The ins element represents an addition to the document. 
  */
@SuppressWarnings("unchecked")
class _HtmlInsBuilder<self_t extends IHtmlTagBuilder<?>>
        extends DecoratedHtmlTagBuilder<self_t> {

    public _HtmlInsBuilder(IXmlTagBuilder o) {
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
