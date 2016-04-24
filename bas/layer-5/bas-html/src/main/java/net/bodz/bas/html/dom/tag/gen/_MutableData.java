package net.bodz.bas.html.dom.tag.gen;

import net.bodz.bas.html.dom.IHtmlTag;
import net.bodz.bas.html.dom.MutableHtmlTag;

/**
  * <!--TOPIC:HTML-->The data element represents its contents, along with a machine-readable form of those contents in the value attribute. 
  */
@SuppressWarnings("unchecked")
public class _MutableData<self_t extends IHtmlTag>
        extends MutableHtmlTag<self_t> {

    public _MutableData(IHtmlTag parent) {
        super(parent, "data");
    }

    /**
      * The value attribute must be present. Its value must be a representation of the element's contents in a machine-readable format. 
      */
    public self_t value(Object val) {
        attr("value", val);
        return (self_t) this;
    }

}
