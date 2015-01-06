package net.bodz.bas.html.dom.tag;

import net.bodz.bas.html.dom.AbstractHtmlTag;
import net.bodz.bas.html.dom.IHtmlTag;

/**
  * <!--TOPIC:HTML-->The option element represents an option in a select element or as part of a list of suggestions in a datalist element. 
  */
@SuppressWarnings("unchecked")
class _HtmlOptionTag<self_t extends IHtmlTag>
        extends AbstractHtmlTag<self_t> {

    public _HtmlOptionTag(IHtmlTag parent, String tagName) {
        super(parent, tagName);
    }

    /**
      * The disabled attribute is a boolean attribute. An option element is disabled if its disabled attribute is present or if it is a child of an optgroup element whose disabled attribute is present. 
      */
    public self_t disabled(Object val) {
        attr("disabled", val);
        return (self_t) this;
    }

    /**
      * The label content attribute, if specified, must not be empty. 
      */
    public self_t label(Object val) {
        attr("label", val);
        return (self_t) this;
    }

    /**
      * The selected attribute is a boolean attribute. It represents the default selectedness of the element. 
      */
    public self_t selected(Object val) {
        attr("selected", val);
        return (self_t) this;
    }

    /**
      * The value attribute provides a value for element. The value of an option element is the value of the value content attribute, if there is one, or, if there is not, the value of the element's text IDL attribute. 
      */
    public self_t value(Object val) {
        attr("value", val);
        return (self_t) this;
    }

}
