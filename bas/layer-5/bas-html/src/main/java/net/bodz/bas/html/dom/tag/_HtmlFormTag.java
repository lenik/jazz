package net.bodz.bas.html.dom.tag;

import net.bodz.bas.html.dom.AbstractHtmlTag;
import net.bodz.bas.html.dom.IHtmlTag;

/**
  * <!--TOPIC:HTML-->The form element represents a collection of form-associated elements, some of which can represent editable values that can be submitted to a server for processing. 
  */
@SuppressWarnings("unchecked")
class _HtmlFormTag<self_t extends IHtmlTag>
        extends AbstractHtmlTag<self_t> {

    public _HtmlFormTag(IHtmlTag parent, String tagName) {
        super(parent, tagName);
    }

    public self_t acceptCharset(Object val) {
        attr("accept-charset", val);
        return (self_t) this;
    }

    public self_t action(Object val) {
        attr("action", val);
        return (self_t) this;
    }

    /**
      * The autocomplete attribute is an enumerated attribute. The attribute has two states. The on keyword maps to the on state, and the off keyword maps to the off state. The attribute may also be omitted. The missing value default is the on state. The off state indicates that by default, form controls in the form will have their autofill field name set to "off"; the on state indicates that by default, form controls in the form will have their autofill field name set to "on". 
      */
    public self_t autocomplete(Object val) {
        attr("autocomplete", val);
        return (self_t) this;
    }

    public self_t enctype(Object val) {
        attr("enctype", val);
        return (self_t) this;
    }

    public self_t method(Object val) {
        attr("method", val);
        return (self_t) this;
    }

    /**
      * The name attribute represents the form's name within the forms collection. The value must not be the empty string, and the value must be unique amongst the form elements in the forms collection that it is in, if any. 
      */
    public self_t name(Object val) {
        attr("name", val);
        return (self_t) this;
    }

    public self_t novalidate(Object val) {
        attr("novalidate", val);
        return (self_t) this;
    }

    public self_t target(Object val) {
        attr("target", val);
        return (self_t) this;
    }

}
