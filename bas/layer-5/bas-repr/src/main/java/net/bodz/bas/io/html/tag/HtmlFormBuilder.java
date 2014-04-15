package net.bodz.bas.io.html.tag;

import net.bodz.bas.io.html.DecoratedHtmlTagBuilder;
import net.bodz.bas.io.xml.IXmlTagBuilder;

/**
  * <!--TOPIC:HTML-->The form element represents a collection of form-associated elements, some of which can represent editable values that can be submitted to a server for processing. 
  */
public class HtmlFormBuilder
        extends DecoratedHtmlTagBuilder<HtmlFormBuilder> {

    public HtmlFormBuilder(IXmlTagBuilder o) {
        super(o);
    }

    public HtmlFormBuilder acceptCharset(String val) {
        attr("accept-charset", val);
        return this;
    }

    public HtmlFormBuilder action(String val) {
        attr("action", val);
        return this;
    }

    /**
      * The autocomplete attribute is an enumerated attribute. The attribute has two states. The on keyword maps to the on state, and the off keyword maps to the off state. The attribute may also be omitted. The missing value default is the on state. The off state indicates that by default, form controls in the form will have their autofill field name set to "off"; the on state indicates that by default, form controls in the form will have their autofill field name set to "on". 
      */
    public HtmlFormBuilder autocomplete(String val) {
        attr("autocomplete", val);
        return this;
    }

    public HtmlFormBuilder enctype(String val) {
        attr("enctype", val);
        return this;
    }

    public HtmlFormBuilder method(String val) {
        attr("method", val);
        return this;
    }

    /**
      * The name attribute represents the form's name within the forms collection. The value must not be the empty string, and the value must be unique amongst the form elements in the forms collection that it is in, if any. 
      */
    public HtmlFormBuilder name(String val) {
        attr("name", val);
        return this;
    }

    public HtmlFormBuilder novalidate(String val) {
        attr("novalidate", val);
        return this;
    }

    public HtmlFormBuilder target(String val) {
        attr("target", val);
        return this;
    }

}
