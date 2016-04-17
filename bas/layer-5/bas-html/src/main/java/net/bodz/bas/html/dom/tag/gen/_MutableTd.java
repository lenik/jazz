package net.bodz.bas.html.dom.tag.gen;

import net.bodz.bas.html.dom.IHtmlTag;
import net.bodz.bas.html.dom.MutableHtmlTag;

/**
  * The td element and its colspan, rowspan, and headers attributes take part in the table model. 
  */
@SuppressWarnings("unchecked")
public class _MutableTd<self_t extends IHtmlTag>
        extends MutableHtmlTag<self_t> {

    public _MutableTd(IHtmlTag parent, String tagName) {
        super(parent, tagName);
    }

    public self_t colspan(Object val) {
        attr("colspan", val);
        return (self_t) this;
    }

    public self_t rowspan(Object val) {
        attr("rowspan", val);
        return (self_t) this;
    }

    public self_t headers(Object val) {
        attr("headers", val);
        return (self_t) this;
    }

}
