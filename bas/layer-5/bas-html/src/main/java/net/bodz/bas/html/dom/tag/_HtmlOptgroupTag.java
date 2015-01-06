package net.bodz.bas.html.dom.tag;

import net.bodz.bas.html.dom.AbstractHtmlTag;
import net.bodz.bas.html.dom.IHtmlTag;

/**
  * <!--TOPIC:HTML--><!-- v2: make optgroups selectable if they have a value. -->The optgroup element represents a group of option elements with a common label. 
  */
@SuppressWarnings("unchecked")
class _HtmlOptgroupTag<self_t extends IHtmlTag>
        extends AbstractHtmlTag<self_t> {

    public _HtmlOptgroupTag(IHtmlTag parent, String tagName) {
        super(parent, tagName);
    }

    /**
      * The disabled attribute is a boolean attribute and can be used to disable a group of option elements together. 
      */
    public self_t disabled(Object val) {
        attr("disabled", val);
        return (self_t) this;
    }

    /**
      * The label attribute must be specified. Its value gives the name of the group, for the purposes of the user interface. User agents should use this attribute's value when labeling the group of option elements in a select element. 
      */
    public self_t label(Object val) {
        attr("label", val);
        return (self_t) this;
    }

}
