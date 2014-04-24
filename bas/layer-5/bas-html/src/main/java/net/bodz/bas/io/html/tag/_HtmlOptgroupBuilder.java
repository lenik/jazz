package net.bodz.bas.io.html.tag;

import net.bodz.bas.io.html.DecoratedHtmlTagBuilder;
import net.bodz.bas.io.html.IHtmlTagBuilder;
import net.bodz.bas.io.xml.IXmlTagBuilder;

/**
  * <!--TOPIC:HTML--><!-- v2: make optgroups selectable if they have a value. -->The optgroup element represents a group of option elements with a common label. 
  */
@SuppressWarnings("unchecked")
class _HtmlOptgroupBuilder<self_t extends IHtmlTagBuilder<?>>
        extends DecoratedHtmlTagBuilder<self_t> {

    public _HtmlOptgroupBuilder(IXmlTagBuilder o) {
        super(o);
    }

    /**
      * The disabled attribute is a boolean attribute and can be used to disable a group of option elements together. 
      */
    public self_t disabled(String val) {
        attr("disabled", val);
        return (self_t) this;
    }

    /**
      * The label attribute must be specified. Its value gives the name of the group, for the purposes of the user interface. User agents should use this attribute's value when labeling the group of option elements in a select element. 
      */
    public self_t label(String val) {
        attr("label", val);
        return (self_t) this;
    }

}
