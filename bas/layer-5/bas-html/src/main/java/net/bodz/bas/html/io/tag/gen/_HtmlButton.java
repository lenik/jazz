package net.bodz.bas.html.io.tag.gen;

import net.bodz.bas.html.io.HtmlDoc;
import net.bodz.bas.html.io.RecHtmlOut;

/**
 * <!--TOPIC:HTML-->The button element represents a button labeled by its contents.
 */
public class _HtmlButton<self_t extends RecHtmlOut<self_t>>
        extends RecHtmlOut<self_t> {

    public _HtmlButton(HtmlDoc doc) {
        super(doc);
    }

    public self_t autofocus(Object val) {
        return attr("autofocus", val);
    }

    public self_t disabled(Object val) {
        return attr("disabled", val);
    }

    /**
     * If the element has a form owner and the element's Document is fully active, the element must
     * submit the form owner from the button element. Reset Button If the element has a form owner
     * and the element's Document is fully active, the element must reset the form owner. Button Do
     * nothing. The form attribute is used to explicitly associate the button element with its form
     * owner. The name attribute represents the element's name. The disabled attribute is used to
     * make the control non-interactive and to prevent its value from being submitted. The autofocus
     * attribute controls focus. The formaction, formenctype, formmethod, formnovalidate, and
     * formtarget attributes are attributes for form submission.
     */
    public self_t form(Object val) {
        return attr("form", val);
    }

    public self_t formaction(Object val) {
        return attr("formaction", val);
    }

    public self_t formenctype(Object val) {
        return attr("formenctype", val);
    }

    public self_t formmethod(Object val) {
        return attr("formmethod", val);
    }

    /**
     * The formnovalidate attribute can be used to make submit buttons that do not trigger the
     * constraint validation.
     */
    public self_t formnovalidate(Object val) {
        return attr("formnovalidate", val);
    }

    public self_t formtarget(Object val) {
        return attr("formtarget", val);
    }

    public self_t name(Object val) {
        return attr("name", val);
    }

    /**
     * The type attribute controls the behavior of the button when it is activated. It is an
     * enumerated attribute. The following table lists the keywords and states for the attribute —
     * the keywords in the left column map to the states in the cell in the second column on the
     * same row as the keyword.
     */
    public self_t type(Object val) {
        return attr("type", val);
    }

    /**
     * The value attribute gives the element's value for the purposes of form submission. The
     * element's value is the value of the element's value attribute, if there is one, or the empty
     * string otherwise.
     */
    public self_t value(Object val) {
        return attr("value", val);
    }

}
