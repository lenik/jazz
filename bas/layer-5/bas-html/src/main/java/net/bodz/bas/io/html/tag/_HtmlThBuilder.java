package net.bodz.bas.io.html.tag;

import net.bodz.bas.io.html.DecoratedHtmlTagBuilder;
import net.bodz.bas.io.html.IHtmlTagBuilder;
import net.bodz.bas.io.xml.IXmlTagBuilder;

/**
  * The tbody elements in this example identify the range of the row groups. 
  */
@SuppressWarnings("unchecked")
class _HtmlThBuilder<self_t extends IHtmlTagBuilder<?>>
        extends DecoratedHtmlTagBuilder<self_t> {

    public _HtmlThBuilder(IXmlTagBuilder o) {
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

    /**
      * The scope attribute's missing value default is the auto state. 
      */
    public self_t scope(String val) {
        attr("scope", val);
        return (self_t) this;
    }

    public self_t abbr(String val) {
        attr("abbr", val);
        return (self_t) this;
    }

}
