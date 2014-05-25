package net.bodz.bas.html.dom.tag;

import net.bodz.bas.html.dom.AbstractHtmlTag;
import net.bodz.bas.html.dom.IHtmlTag;

/**
  * The tbody elements in this example identify the range of the row groups. 
  */
@SuppressWarnings("unchecked")
class _HtmlThTag<self_t extends IHtmlTag>
        extends AbstractHtmlTag<self_t> {

    public _HtmlThTag(IHtmlTag parent, String tagName) {
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