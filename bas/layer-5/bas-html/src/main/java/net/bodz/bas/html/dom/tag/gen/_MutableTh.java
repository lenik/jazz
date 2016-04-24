package net.bodz.bas.html.dom.tag.gen;

import net.bodz.bas.html.dom.IHtmlTag;
import net.bodz.bas.html.dom.MutableHtmlTag;

/**
  * The tbody elements in this example identify the range of the row groups. 
  */
@SuppressWarnings("unchecked")
public class _MutableTh<self_t extends IHtmlTag>
        extends MutableHtmlTag<self_t> {

    public _MutableTh(IHtmlTag parent) {
        super(parent, "th");
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

    /**
      * The scope attribute's missing value default is the auto state. 
      */
    public self_t scope(Object val) {
        attr("scope", val);
        return (self_t) this;
    }

    public self_t abbr(Object val) {
        attr("abbr", val);
        return (self_t) this;
    }

}
