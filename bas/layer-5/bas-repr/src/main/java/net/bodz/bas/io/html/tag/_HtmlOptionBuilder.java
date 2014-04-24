package net.bodz.bas.io.html.tag;

import net.bodz.bas.io.html.DecoratedHtmlTagBuilder;
import net.bodz.bas.io.html.IHtmlTagBuilder;
import net.bodz.bas.io.xml.IXmlTagBuilder;

/**
  * <!--TOPIC:HTML-->The option element represents an option in a select element or as part of a list of suggestions in a datalist element. 
  */
@SuppressWarnings("unchecked")
class _HtmlOptionBuilder<self_t extends IHtmlTagBuilder<?>>
        extends DecoratedHtmlTagBuilder<self_t> {

    public _HtmlOptionBuilder(IXmlTagBuilder o) {
        super(o);
    }

    /**
      * The disabled attribute is a boolean attribute. An option element is disabled if its disabled attribute is present or if it is a child of an optgroup element whose disabled attribute is present. 
      */
    public self_t disabled(String val) {
        attr("disabled", val);
        return (self_t) this;
    }

    /**
      * The label content attribute, if specified, must not be empty. 
      */
    public self_t label(String val) {
        attr("label", val);
        return (self_t) this;
    }

    /**
      * The selected attribute is a boolean attribute. It represents the default selectedness of the element. 
      */
    public self_t selected(String val) {
        attr("selected", val);
        return (self_t) this;
    }

    /**
      * The value attribute provides a value for element. The value of an option element is the value of the value content attribute, if there is one, or, if there is not, the value of the element's text IDL attribute. 
      */
    public self_t value(String val) {
        attr("value", val);
        return (self_t) this;
    }

}
