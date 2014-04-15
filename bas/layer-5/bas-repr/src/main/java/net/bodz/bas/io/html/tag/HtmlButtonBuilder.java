package net.bodz.bas.io.html.tag;

import net.bodz.bas.io.html.DecoratedHtmlTagBuilder;
import net.bodz.bas.io.xml.IXmlTagBuilder;

/**
  * <!--TOPIC:HTML-->The button element represents a button labeled by its contents. 
  */
public class HtmlButtonBuilder
        extends DecoratedHtmlTagBuilder<HtmlButtonBuilder> {

    public HtmlButtonBuilder(IXmlTagBuilder o) {
        super(o);
    }

    public HtmlButtonBuilder autofocus(String val) {
        attr("autofocus", val);
        return this;
    }

    public HtmlButtonBuilder disabled(String val) {
        attr("disabled", val);
        return this;
    }

    /**
      * If the element has a form owner and the element's Document is fully active, the element must submit the form owner from the button element. Reset Button If the element has a form owner and the element's Document is fully active, the element must reset the form owner. Button Do nothing. The form attribute is used to explicitly associate the button element with its form owner. The name attribute represents the element's name. The disabled attribute is used to make the control non-interactive and to prevent its value from being submitted. The autofocus attribute controls focus. The formaction, formenctype, formmethod, formnovalidate, and formtarget attributes are attributes for form submission. 
      */
    public HtmlButtonBuilder form(String val) {
        attr("form", val);
        return this;
    }

    public HtmlButtonBuilder formaction(String val) {
        attr("formaction", val);
        return this;
    }

    public HtmlButtonBuilder formenctype(String val) {
        attr("formenctype", val);
        return this;
    }

    public HtmlButtonBuilder formmethod(String val) {
        attr("formmethod", val);
        return this;
    }

    /**
      * The formnovalidate attribute can be used to make submit buttons that do not trigger the constraint validation. 
      */
    public HtmlButtonBuilder formnovalidate(String val) {
        attr("formnovalidate", val);
        return this;
    }

    public HtmlButtonBuilder formtarget(String val) {
        attr("formtarget", val);
        return this;
    }

    public HtmlButtonBuilder name(String val) {
        attr("name", val);
        return this;
    }

    /**
      * The type attribute controls the behavior of the button when it is activated. It is an enumerated attribute. The following table lists the keywords and states for the attribute — the keywords in the left column map to the states in the cell in the second column on the same row as the keyword. 
      */
    public HtmlButtonBuilder type(String val) {
        attr("type", val);
        return this;
    }

    /**
      * The value attribute gives the element's value for the purposes of form submission. The element's value is the value of the element's value attribute, if there is one, or the empty string otherwise. 
      */
    public HtmlButtonBuilder value(String val) {
        attr("value", val);
        return this;
    }

}
