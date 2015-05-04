package net.bodz.bas.html.dom.tag;

import net.bodz.bas.html.dom.AbstractHtmlTag;
import net.bodz.bas.html.dom.IHtmlTag;

/**
  * <!--TOPIC:HTML-->The select element represents a control for selecting amongst a set of options. 
  */
@SuppressWarnings("unchecked")
public class _HtmlSelectTag<self_t extends IHtmlTag>
        extends AbstractHtmlTag<self_t> {

    protected _HtmlSelectTag(IHtmlTag parent, String tagName) {
        super(parent, tagName);
    }

    public self_t autofocus(Object val) {
        attr("autofocus", val);
        return (self_t) this;
    }

    public self_t disabled(Object val) {
        attr("disabled", val);
        return (self_t) this;
    }

    /**
      * The form attribute is used to explicitly associate the select element with its form owner. The name attribute represents the element's name. The disabled attribute is used to make the control non-interactive and to prevent its value from being submitted. The autofocus attribute controls focus. 
      */
    public self_t form(Object val) {
        attr("form", val);
        return (self_t) this;
    }

    /**
      * The multiple attribute is a boolean attribute. If the attribute is present, then the select element represents a control for selecting zero or more options from the list of options. If the attribute is absent, then the select element represents a control for selecting a single option from the list of options. 
      */
    public self_t multiple(Object val) {
        attr("multiple", val);
        return (self_t) this;
    }

    public self_t name(Object val) {
        attr("name", val);
        return (self_t) this;
    }

    /**
      * The required attribute is a boolean attribute. When specified, the user will be required to select a value before submitting the form. 
      */
    public self_t required(Object val) {
        attr("required", val);
        return (self_t) this;
    }

    /**
      * The size attribute gives the number of options to show to the user. The size attribute, if specified, must have a value that is a valid non-negative integer greater than zero. 
      */
    public self_t size(Object val) {
        attr("size", val);
        return (self_t) this;
    }

}
