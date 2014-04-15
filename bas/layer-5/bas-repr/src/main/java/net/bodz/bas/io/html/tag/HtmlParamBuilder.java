package net.bodz.bas.io.html.tag;

import net.bodz.bas.io.html.DecoratedHtmlTagBuilder;
import net.bodz.bas.io.xml.IXmlTagBuilder;

/**
  * <!--TOPIC:HTML-->The param element defines parameters for plugins invoked by object elements. It does not represent anything on its own. 
  */
public class HtmlParamBuilder
        extends DecoratedHtmlTagBuilder<HtmlParamBuilder> {

    public HtmlParamBuilder(IXmlTagBuilder o) {
        super(o);
    }

    /**
      * The name attribute gives the name of the parameter. 
      */
    public HtmlParamBuilder name(String val) {
        attr("name", val);
        return this;
    }

    /**
      * The value attribute gives the value of the parameter. 
      */
    public HtmlParamBuilder value(String val) {
        attr("value", val);
        return this;
    }

}
