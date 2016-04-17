package net.bodz.bas.html.io.tag.gen;

import net.bodz.bas.html.io.HtmlDoc;
import net.bodz.bas.html.io.RecHtmlOut;

/**
 * The div elements used in the code samples above and below are not intended to convey any semantic
 * meaning and are used only to create a non-inline rendering of the grouped fieldset controls.
 */
public class _HtmlFieldset<self_t extends RecHtmlOut<self_t>>
        extends RecHtmlOut<self_t> {

    public _HtmlFieldset(HtmlDoc doc) {
        super(doc);
    }

    /**
     * The disabled attribute, when specified, causes all the form control descendants of the
     * fieldset element, excluding those that are descendants of the fieldset element's first legend
     * element child, if any, to be disabled.
     */
    public self_t disabled(Object val) {
        return attr("disabled", val);
    }

    /**
     * The form attribute is used to explicitly associate the fieldset element with its form owner.
     * The name attribute represents the element's name.
     */
    public self_t form(Object val) {
        return attr("form", val);
    }

    public self_t name(Object val) {
        return attr("name", val);
    }

}
