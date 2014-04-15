package net.bodz.bas.io.html.tag;

import net.bodz.bas.io.html.DecoratedHtmlTagBuilder;
import net.bodz.bas.io.xml.IXmlTagBuilder;

/**
  * <!--TOPIC:HTML-->The output element represents the result of a calculation or user action. 
  */
public class HtmlOutputBuilder
        extends DecoratedHtmlTagBuilder<HtmlOutputBuilder> {

    public HtmlOutputBuilder(IXmlTagBuilder o) {
        super(o);
    }

    /**
      * The for content attribute allows an explicit relationship to be made between the result of a calculation and the elements that represent the values that went into the calculation or that otherwise influenced the calculation. The for attribute, if specified, must contain a string consisting of an unordered set of unique space-separated tokens that are case-sensitive, each of which must have the value of an ID of an element in the same Document. 
      */
    public HtmlOutputBuilder for_(String val) {
        attr("for", val);
        return this;
    }

    /**
      * The form attribute is used to explicitly associate the output element with its form owner. The name attribute represents the element's name. 
      */
    public HtmlOutputBuilder form(String val) {
        attr("form", val);
        return this;
    }

    public HtmlOutputBuilder name(String val) {
        attr("name", val);
        return this;
    }

}
