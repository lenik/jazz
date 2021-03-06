package net.bodz.bas.html.dom.tag.gen;

import net.bodz.bas.html.dom.IHtmlTag;
import net.bodz.bas.html.dom.MutableHtmlTag;

/**
  * The canvas element has two attributes to control the size of the element's bitmap: width and height. These attributes, when specified, must have values that are valid non-negative integers. The rules for parsing non-negative integers must be used to obtain their numeric values. If an attribute is missing, or if parsing its value returns an error, then the default value must be used instead. The width attribute defaults to 300, and the height attribute defaults to 150. 
  */
@SuppressWarnings("unchecked")
public class _MutableCanvas<self_t extends IHtmlTag>
        extends MutableHtmlTag<self_t> {

    public _MutableCanvas(IHtmlTag parent) {
        super(parent, "canvas");
    }

    /**
      * The canvas element has two attributes to control the size of the element's bitmap: width and height. These attributes, when specified, must have values that are valid non-negative integers. The rules for parsing non-negative integers must be used to obtain their numeric values. If an attribute is missing, or if parsing its value returns an error, then the default value must be used instead. The width attribute defaults to 300, and the height attribute defaults to 150. 
      */
    public self_t width(Object val) {
        attr("width", val);
        return (self_t) this;
    }

    public self_t height(Object val) {
        attr("height", val);
        return (self_t) this;
    }

}
