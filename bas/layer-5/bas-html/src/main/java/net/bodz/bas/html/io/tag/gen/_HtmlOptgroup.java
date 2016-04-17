package net.bodz.bas.html.io.tag.gen;

import net.bodz.bas.html.io.HtmlDoc;
import net.bodz.bas.html.io.RecHtmlOut;

/**
 * <!--TOPIC:HTML--><!-- v2: make optgroups selectable if they have a value. -->The optgroup element
 * represents a group of option elements with a common label.
 */
public class _HtmlOptgroup<self_t extends RecHtmlOut<self_t>>
        extends RecHtmlOut<self_t> {

    public _HtmlOptgroup(HtmlDoc doc) {
        super(doc);
    }

    /**
     * The disabled attribute is a boolean attribute and can be used to disable a group of option
     * elements together.
     */
    public self_t disabled(Object val) {
        return attr("disabled", val);
    }

    /**
     * The label attribute must be specified. Its value gives the name of the group, for the
     * purposes of the user interface. User agents should use this attribute's value when labeling
     * the group of option elements in a select element.
     */
    public self_t label(Object val) {
        return attr("label", val);
    }

}
