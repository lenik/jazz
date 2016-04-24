package net.bodz.bas.html.dom.tag.gen;

import net.bodz.bas.html.dom.IHtmlTag;
import net.bodz.bas.html.dom.MutableHtmlTag;

/**
  * <!--TOPIC:HTML-->The button element represents a button labeled by its contents. 
  */
@SuppressWarnings("unchecked")
public class _MutableButton<self_t extends IHtmlTag>
        extends MutableHtmlTag<self_t> {

    public _MutableButton(IHtmlTag parent) {
        super(parent, "button");
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
      * If the element has a form owner and the element's Document is fully active, the element must submit the form owner from the button element. Reset Button If the element has a form owner and the element's Document is fully active, the element must reset the form owner. Button Do nothing. The form attribute is used to explicitly associate the button element with its form owner. The name attribute represents the element's name. The disabled attribute is used to make the control non-interactive and to prevent its value from being submitted. The autofocus attribute controls focus. The formaction, formenctype, formmethod, formnovalidate, and formtarget attributes are attributes for form submission. 
      */
    public self_t form(Object val) {
        attr("form", val);
        return (self_t) this;
    }

    public self_t formaction(Object val) {
        attr("formaction", val);
        return (self_t) this;
    }

    public self_t formenctype(Object val) {
        attr("formenctype", val);
        return (self_t) this;
    }

    public self_t formmethod(Object val) {
        attr("formmethod", val);
        return (self_t) this;
    }

    /**
      * The formnovalidate attribute can be used to make submit buttons that do not trigger the constraint validation. 
      */
    public self_t formnovalidate(Object val) {
        attr("formnovalidate", val);
        return (self_t) this;
    }

    public self_t formtarget(Object val) {
        attr("formtarget", val);
        return (self_t) this;
    }

    public self_t name(Object val) {
        attr("name", val);
        return (self_t) this;
    }

    /**
      * The type attribute controls the behavior of the button when it is activated. It is an enumerated attribute. The following table lists the keywords and states for the attribute — the keywords in the left column map to the states in the cell in the second column on the same row as the keyword. 
      */
    public self_t type(Object val) {
        attr("type", val);
        return (self_t) this;
    }

    /**
      * The value attribute gives the element's value for the purposes of form submission. The element's value is the value of the element's value attribute, if there is one, or the empty string otherwise. 
      */
    public self_t value(Object val) {
        attr("value", val);
        return (self_t) this;
    }

}
