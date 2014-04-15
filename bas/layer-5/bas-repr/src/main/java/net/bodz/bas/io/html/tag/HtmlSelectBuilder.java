package net.bodz.bas.io.html.tag;

import net.bodz.bas.io.html.DecoratedHtmlTagBuilder;
import net.bodz.bas.io.xml.IXmlTagBuilder;

/**
  * -->The select element represents a control for selecting amongst a set of options. 
  */
public class HtmlSelectBuilder
        extends DecoratedHtmlTagBuilder<HtmlSelectBuilder> {

    public HtmlSelectBuilder(IXmlTagBuilder o) {
        super(o);
    }

    public HtmlSelectBuilder autofocus(String val) {
        attr("autofocus", val);
        return this;
    }

    public HtmlSelectBuilder disabled(String val) {
        attr("disabled", val);
        return this;
    }

    /**
      * The form attribute is used to explicitly associate the select element with its form owner. The name attribute represents the element's name. The disabled attribute is used to make the control non-interactive and to prevent its value from being submitted. The autofocus attribute controls focus. 
      */
    public HtmlSelectBuilder form(String val) {
        attr("form", val);
        return this;
    }

    /**
      * The multiple attribute is a boolean attribute. If the attribute is present, then the select element represents a control for selecting zero or more options from the list of options. If the attribute is absent, then the select element represents a control for selecting a single option from the list of options. 
      */
    public HtmlSelectBuilder multiple(String val) {
        attr("multiple", val);
        return this;
    }

    public HtmlSelectBuilder name(String val) {
        attr("name", val);
        return this;
    }

    /**
      * The required attribute is a boolean attribute. When specified, the user will be required to select a value before submitting the form. 
      */
    public HtmlSelectBuilder required(String val) {
        attr("required", val);
        return this;
    }

    /**
      * The size attribute gives the number of options to show to the user. The size attribute, if specified, must have a value that is a valid non-negative integer greater than zero. 
      */
    public HtmlSelectBuilder size(String val) {
        attr("size", val);
        return this;
    }

}
