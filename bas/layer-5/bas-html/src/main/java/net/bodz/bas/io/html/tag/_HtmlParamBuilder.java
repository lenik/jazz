package net.bodz.bas.io.html.tag;

import net.bodz.bas.io.html.DecoratedHtmlTagBuilder;
import net.bodz.bas.io.html.IHtmlTagBuilder;
import net.bodz.bas.io.xml.IXmlTagBuilder;

/**
  * <!--TOPIC:HTML-->The param element defines parameters for plugins invoked by object elements. It does not represent anything on its own. 
  */
@SuppressWarnings("unchecked")
class _HtmlParamBuilder<self_t extends IHtmlTagBuilder<?>>
        extends DecoratedHtmlTagBuilder<self_t> {

    public _HtmlParamBuilder(IXmlTagBuilder o) {
        super(o);
    }

    /**
      * The name attribute gives the name of the parameter. 
      */
    public self_t name(String val) {
        attr("name", val);
        return (self_t) this;
    }

    /**
      * The value attribute gives the value of the parameter. 
      */
    public self_t value(String val) {
        attr("value", val);
        return (self_t) this;
    }

}
