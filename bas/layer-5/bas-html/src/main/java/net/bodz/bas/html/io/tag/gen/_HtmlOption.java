package net.bodz.bas.html.io.tag.gen;

import net.bodz.bas.html.io.HtmlDoc;
import net.bodz.bas.html.io.RecHtmlOut;

/**
 * <!--TOPIC:HTML-->The option element represents an option in a select element or as part of a list
 * of suggestions in a datalist element.
 */
public class _HtmlOption<self_t extends RecHtmlOut<self_t>>
        extends RecHtmlOut<self_t> {

    public _HtmlOption(HtmlDoc doc) {
        super(doc);
    }

    /**
     * The disabled attribute is a boolean attribute. An option element is disabled if its disabled
     * attribute is present or if it is a child of an optgroup element whose disabled attribute is
     * present.
     */
    public self_t disabled(Object val) {
        return attr("disabled", val);
    }

    /**
     * The label content attribute, if specified, must not be empty.
     */
    public self_t label(Object val) {
        return attr("label", val);
    }

    /**
     * The selected attribute is a boolean attribute. It represents the default selectedness of the
     * element.
     */
    public self_t selected(Object val) {
        return attr("selected", val);
    }

    /**
     * The value attribute provides a value for element. The value of an option element is the value
     * of the value content attribute, if there is one, or, if there is not, the value of the
     * element's text IDL attribute.
     */
    public self_t value(Object val) {
        return attr("value", val);
    }

}
