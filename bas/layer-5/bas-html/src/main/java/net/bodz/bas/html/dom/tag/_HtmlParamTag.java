package net.bodz.bas.html.dom.tag;

import net.bodz.bas.html.dom.AbstractHtmlTag;
import net.bodz.bas.html.dom.IHtmlTag;

/**
  * <!--TOPIC:HTML-->The param element defines parameters for plugins invoked by object elements. It does not represent anything on its own. 
  */
@SuppressWarnings("unchecked")
class _HtmlParamTag<self_t extends IHtmlTag>
        extends AbstractHtmlTag<self_t> {

    public _HtmlParamTag(IHtmlTag parent, String tagName) {
        super(parent, tagName);
    }

    /**
      * The name attribute gives the name of the parameter. 
      */
    public self_t name(Object val) {
        attr("name", val);
        return (self_t) this;
    }

    /**
      * The value attribute gives the value of the parameter. 
      */
    public self_t value(Object val) {
        attr("value", val);
        return (self_t) this;
    }

}
