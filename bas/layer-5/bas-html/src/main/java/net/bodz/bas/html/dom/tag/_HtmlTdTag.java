package net.bodz.bas.html.dom.tag;

import net.bodz.bas.html.dom.AbstractHtmlTag;
import net.bodz.bas.html.dom.IHtmlTag;

/**
  * The td element and its colspan, rowspan, and headers attributes take part in the table model. 
  */
@SuppressWarnings("unchecked")
class _HtmlTdTag<self_t extends IHtmlTag>
        extends AbstractHtmlTag<self_t> {

    public _HtmlTdTag(IHtmlTag parent, String tagName) {
        super(parent, tagName);
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
