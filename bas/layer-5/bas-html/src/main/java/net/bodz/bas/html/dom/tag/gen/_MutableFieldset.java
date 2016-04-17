package net.bodz.bas.html.dom.tag.gen;

import net.bodz.bas.html.dom.IHtmlTag;
import net.bodz.bas.html.dom.MutableHtmlTag;

/**
  * The div elements used in the code samples above and below are not intended to convey any semantic meaning and are used only to create a non-inline rendering of the grouped fieldset controls. 
  */
@SuppressWarnings("unchecked")
public class _MutableFieldset<self_t extends IHtmlTag>
        extends MutableHtmlTag<self_t> {

    public _MutableFieldset(IHtmlTag parent, String tagName) {
        super(parent, tagName);
    }

    /**
      * The disabled attribute, when specified, causes all the form control descendants of the fieldset element, excluding those that are descendants of the fieldset element's first legend element child, if any, to be disabled. 
      */
    public self_t disabled(Object val) {
        attr("disabled", val);
        return (self_t) this;
    }

    /**
      * The form attribute is used to explicitly associate the fieldset element with its form owner. The name attribute represents the element's name. 
      */
    public self_t form(Object val) {
        attr("form", val);
        return (self_t) this;
    }

    public self_t name(Object val) {
        attr("name", val);
        return (self_t) this;
    }

}
