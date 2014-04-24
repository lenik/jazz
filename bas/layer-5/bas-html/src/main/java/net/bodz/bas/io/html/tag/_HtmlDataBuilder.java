package net.bodz.bas.io.html.tag;

import net.bodz.bas.io.html.DecoratedHtmlTagBuilder;
import net.bodz.bas.io.html.IHtmlTagBuilder;
import net.bodz.bas.io.xml.IXmlTagBuilder;

/**
  * <!--TOPIC:HTML-->The data element represents its contents, along with a machine-readable form of those contents in the value attribute. 
  */
@SuppressWarnings("unchecked")
class _HtmlDataBuilder<self_t extends IHtmlTagBuilder<?>>
        extends DecoratedHtmlTagBuilder<self_t> {

    public _HtmlDataBuilder(IXmlTagBuilder o) {
        super(o);
    }

    /**
      * The value attribute must be present. Its value must be a representation of the element's contents in a machine-readable format. 
      */
    public self_t value(String val) {
        attr("value", val);
        return (self_t) this;
    }

}
