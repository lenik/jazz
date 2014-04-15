package net.bodz.bas.io.html.tag;

import net.bodz.bas.io.html.DecoratedHtmlTagBuilder;
import net.bodz.bas.io.xml.IXmlTagBuilder;

/**
  * The progress element is the wrong element to use for something that is just a gauge, as opposed to task progress. For instance, indicating disk space usage using progress would be inappropriate. Instead, the meter element is available for such use cases. 
  */
public class HtmlProgressBuilder
        extends DecoratedHtmlTagBuilder<HtmlProgressBuilder> {

    public HtmlProgressBuilder(IXmlTagBuilder o) {
        super(o);
    }

    /**
      * The value and max attributes, when present, must have values that are valid floating-point numbers. The value attribute, if present, must have a value equal to or greater than zero, and less than or equal to the value of the max attribute, if present, or 1.0, otherwise. The max attribute, if present, must have a value greater than zero. 
      */
    public HtmlProgressBuilder value(String val) {
        attr("value", val);
        return this;
    }

    public HtmlProgressBuilder max(String val) {
        attr("max", val);
        return this;
    }

}
