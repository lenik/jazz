package net.bodz.bas.html.dom.tag.gen;

import net.bodz.bas.html.dom.IHtmlTag;
import net.bodz.bas.html.dom.MutableHtmlTag;

/**
  * The progress element is the wrong element to use for something that is just a gauge, as opposed to task progress. For instance, indicating disk space usage using progress would be inappropriate. Instead, the meter element is available for such use cases. 
  */
@SuppressWarnings("unchecked")
public class _MutableProgress<self_t extends IHtmlTag>
        extends MutableHtmlTag<self_t> {

    public _MutableProgress(IHtmlTag parent) {
        super(parent, "progress");
    }

    /**
      * The value and max attributes, when present, must have values that are valid floating-point numbers. The value attribute, if present, must have a value equal to or greater than zero, and less than or equal to the value of the max attribute, if present, or 1.0, otherwise. The max attribute, if present, must have a value greater than zero. 
      */
    public self_t value(Object val) {
        attr("value", val);
        return (self_t) this;
    }

    public self_t max(Object val) {
        attr("max", val);
        return (self_t) this;
    }

}
