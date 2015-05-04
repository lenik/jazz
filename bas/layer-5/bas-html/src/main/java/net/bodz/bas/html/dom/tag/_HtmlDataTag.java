package net.bodz.bas.html.dom.tag;

import net.bodz.bas.html.dom.AbstractHtmlTag;
import net.bodz.bas.html.dom.IHtmlTag;

/**
  * <!--TOPIC:HTML-->The data element represents its contents, along with a machine-readable form of those contents in the value attribute. 
  */
@SuppressWarnings("unchecked")
public class _HtmlDataTag<self_t extends IHtmlTag>
        extends AbstractHtmlTag<self_t> {

    protected _HtmlDataTag(IHtmlTag parent, String tagName) {
        super(parent, tagName);
    }

    /**
      * The value attribute must be present. Its value must be a representation of the element's contents in a machine-readable format. 
      */
    public self_t value(Object val) {
        attr("value", val);
        return (self_t) this;
    }

}
