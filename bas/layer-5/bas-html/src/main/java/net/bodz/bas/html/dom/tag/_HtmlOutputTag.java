package net.bodz.bas.html.dom.tag;

import net.bodz.bas.html.dom.AbstractHtmlTag;
import net.bodz.bas.html.dom.IHtmlTag;

/**
  * <!--TOPIC:HTML-->The output element represents the result of a calculation or user action. 
  */
@SuppressWarnings("unchecked")
class _HtmlOutputTag<self_t extends IHtmlTag>
        extends AbstractHtmlTag<self_t> {

    public _HtmlOutputTag(IHtmlTag parent, String tagName) {
        super(parent, tagName);
    }

    /**
      * The for content attribute allows an explicit relationship to be made between the result of a calculation and the elements that represent the values that went into the calculation or that otherwise influenced the calculation. The for attribute, if specified, must contain a string consisting of an unordered set of unique space-separated tokens that are case-sensitive, each of which must have the value of an ID of an element in the same Document. 
      */
    public self_t for_(String val) {
        attr("for", val);
        return (self_t) this;
    }

    /**
      * The form attribute is used to explicitly associate the output element with its form owner. The name attribute represents the element's name. 
      */
    public self_t form(String val) {
        attr("form", val);
        return (self_t) this;
    }

    public self_t name(String val) {
        attr("name", val);
        return (self_t) this;
    }

}
