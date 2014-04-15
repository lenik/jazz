package net.bodz.bas.io.html.tag;

import net.bodz.bas.io.html.DecoratedHtmlTagBuilder;
import net.bodz.bas.io.xml.IXmlTagBuilder;

/**
  * <!--TOPIC:HTML-->The data element represents its contents, along with a machine-readable form of those contents in the value attribute. 
  */
public class HtmlDataBuilder
        extends DecoratedHtmlTagBuilder<HtmlDataBuilder> {

    public HtmlDataBuilder(IXmlTagBuilder o) {
        super(o);
    }

    /**
      * The value attribute must be present. Its value must be a representation of the element's contents in a machine-readable format. 
      */
    public HtmlDataBuilder value(String val) {
        attr("value", val);
        return this;
    }

}
