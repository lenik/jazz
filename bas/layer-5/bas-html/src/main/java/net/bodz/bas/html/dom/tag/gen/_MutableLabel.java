package net.bodz.bas.html.dom.tag.gen;

import net.bodz.bas.html.dom.IHtmlTag;
import net.bodz.bas.html.dom.MutableHtmlTag;

/**
  * The label element's exact default presentation and behavior, in particular what its activation behavior might be, if anything, should match the platform's label behavior. The activation behavior of a label element for events targeted at interactive content descendants of a label element, and any descendants of those interactive content descendants, must be to do nothing. 
  */
@SuppressWarnings("unchecked")
public class _MutableLabel<self_t extends IHtmlTag>
        extends MutableHtmlTag<self_t> {

    public _MutableLabel(IHtmlTag parent) {
        super(parent, "label");
    }

    /**
      * The form attribute is used to explicitly associate the label element with its form owner. 
      */
    public self_t form(Object val) {
        attr("form", val);
        return (self_t) this;
    }

    /**
      * The for attribute may be specified to indicate a form control with which the caption is to be associated. If the attribute is specified, the attribute's value must be the ID of a labelable element in the same Document as the label element. If the attribute is specified and there is an element in the Document whose ID is equal to the value of the for attribute, and the first such element is a labelable element, then that element is the label element's labeled control. 
      */
    public self_t for_(Object val) {
        attr("for", val);
        return (self_t) this;
    }

}
