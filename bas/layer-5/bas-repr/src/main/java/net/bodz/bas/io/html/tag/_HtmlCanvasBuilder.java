package net.bodz.bas.io.html.tag;

import net.bodz.bas.io.html.DecoratedHtmlTagBuilder;
import net.bodz.bas.io.html.IHtmlTagBuilder;
import net.bodz.bas.io.xml.IXmlTagBuilder;

/**
  * The canvas element has two attributes to control the size of the element's bitmap: width and height. These attributes, when specified, must have values that are valid non-negative integers. The rules for parsing non-negative integers must be used to obtain their numeric values. If an attribute is missing, or if parsing its value returns an error, then the default value must be used instead. The width attribute defaults to 300, and the height attribute defaults to 150. 
  */
@SuppressWarnings("unchecked")
class _HtmlCanvasBuilder<self_t extends IHtmlTagBuilder<?>>
        extends DecoratedHtmlTagBuilder<self_t> {

    public _HtmlCanvasBuilder(IXmlTagBuilder o) {
        super(o);
    }

    /**
      * The canvas element has two attributes to control the size of the element's bitmap: width and height. These attributes, when specified, must have values that are valid non-negative integers. The rules for parsing non-negative integers must be used to obtain their numeric values. If an attribute is missing, or if parsing its value returns an error, then the default value must be used instead. The width attribute defaults to 300, and the height attribute defaults to 150. 
      */
    public self_t width(String val) {
        attr("width", val);
        return (self_t) this;
    }

    public self_t height(String val) {
        attr("height", val);
        return (self_t) this;
    }

}
