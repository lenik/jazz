package net.bodz.bas.html.io.tag.gen;

import net.bodz.bas.html.io.HtmlDoc;
import net.bodz.bas.html.io.AbstractRecHtmlOut;

/**
 * <!--TOPIC:HTML-->The select element represents a control for selecting amongst a set of options.
 */
public class _HtmlSelect<self_t extends AbstractRecHtmlOut<self_t>>
        extends AbstractRecHtmlOut<self_t> {

    public _HtmlSelect(HtmlDoc doc) {
        super(doc);
    }

    public self_t autofocus(Object val) {
        return attr("autofocus", val);
    }

    public self_t disabled(Object val) {
        return attr("disabled", val);
    }

    /**
     * The form attribute is used to explicitly associate the select element with its form owner.
     * The name attribute represents the element's name. The disabled attribute is used to make the
     * control non-interactive and to prevent its value from being submitted. The autofocus
     * attribute controls focus.
     */
    public self_t form(Object val) {
        return attr("form", val);
    }

    /**
     * The multiple attribute is a boolean attribute. If the attribute is present, then the select
     * element represents a control for selecting zero or more options from the list of options. If
     * the attribute is absent, then the select element represents a control for selecting a single
     * option from the list of options.
     */
    public self_t multiple(Object val) {
        return attr("multiple", val);
    }

    public self_t name(Object val) {
        return attr("name", val);
    }

    /**
     * The required attribute is a boolean attribute. When specified, the user will be required to
     * select a value before submitting the form.
     */
    public self_t required(Object val) {
        return attr("required", val);
    }

    /**
     * The size attribute gives the number of options to show to the user. The size attribute, if
     * specified, must have a value that is a valid non-negative integer greater than zero.
     */
    public self_t size(Object val) {
        return attr("size", val);
    }

}
