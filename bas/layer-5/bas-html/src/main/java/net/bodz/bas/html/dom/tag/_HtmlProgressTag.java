package net.bodz.bas.html.dom.tag;

import net.bodz.bas.html.dom.AbstractHtmlTag;
import net.bodz.bas.html.dom.IHtmlTag;

/**
  * The progress element is the wrong element to use for something that is just a gauge, as opposed to task progress. For instance, indicating disk space usage using progress would be inappropriate. Instead, the meter element is available for such use cases. 
  */
@SuppressWarnings("unchecked")
class _HtmlProgressTag<self_t extends IHtmlTag>
        extends AbstractHtmlTag<self_t> {

    public _HtmlProgressTag(IHtmlTag parent, String tagName) {
        super(parent, tagName);
    }

    /**
      * The value and max attributes, when present, must have values that are valid floating-point numbers. The value attribute, if present, must have a value equal to or greater than zero, and less than or equal to the value of the max attribute, if present, or 1.0, otherwise. The max attribute, if present, must have a value greater than zero. 
      */
    public self_t value(String val) {
        attr("value", val);
        return (self_t) this;
    }

    public self_t max(String val) {
        attr("max", val);
        return (self_t) this;
    }

}
