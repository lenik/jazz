package net.bodz.bas.io.html.tag;

import net.bodz.bas.io.html.DecoratedHtmlTagBuilder;
import net.bodz.bas.io.xml.IXmlTagBuilder;

/**
  * <!--TOPIC:HTML-->The option element represents an option in a select element or as part of a list of suggestions in a datalist element. 
  */
public class HtmlOptionBuilder
        extends DecoratedHtmlTagBuilder<HtmlOptionBuilder> {

    public HtmlOptionBuilder(IXmlTagBuilder o) {
        super(o);
    }

    /**
      * The disabled attribute is a boolean attribute. An option element is disabled if its disabled attribute is present or if it is a child of an optgroup element whose disabled attribute is present. 
      */
    public HtmlOptionBuilder disabled(String val) {
        attr("disabled", val);
        return this;
    }

    /**
      * The label content attribute, if specified, must not be empty. 
      */
    public HtmlOptionBuilder label(String val) {
        attr("label", val);
        return this;
    }

    /**
      * The selected attribute is a boolean attribute. It represents the default selectedness of the element. 
      */
    public HtmlOptionBuilder selected(String val) {
        attr("selected", val);
        return this;
    }

    /**
      * The value attribute provides a value for element. The value of an option element is the value of the value content attribute, if there is one, or, if there is not, the value of the element's text IDL attribute. 
      */
    public HtmlOptionBuilder value(String val) {
        attr("value", val);
        return this;
    }

}
