package net.bodz.bas.html.io.tag.gen;

import net.bodz.bas.html.io.HtmlDoc;
import net.bodz.bas.html.io.AbstractRecHtmlOut;

/**
 * <!--TOPIC:HTML-->The form element represents a collection of form-associated elements, some of
 * which can represent editable values that can be submitted to a server for processing.
 */
public class _HtmlForm<self_t extends AbstractRecHtmlOut<self_t>>
        extends AbstractRecHtmlOut<self_t> {

    public _HtmlForm(HtmlDoc doc) {
        super(doc);
    }

    public self_t acceptCharset(Object val) {
        return attr("accept-charset", val);
    }

    public self_t action(Object val) {
        return attr("action", val);
    }

    /**
     * The autocomplete attribute is an enumerated attribute. The attribute has two states. The on
     * keyword maps to the on state, and the off keyword maps to the off state. The attribute may
     * also be omitted. The missing value default is the on state. The off state indicates that by
     * default, form controls in the form will have their autofill field name set to "off"; the on
     * state indicates that by default, form controls in the form will have their autofill field
     * name set to "on".
     */
    public self_t autocomplete(Object val) {
        return attr("autocomplete", val);
    }

    public self_t enctype(Object val) {
        return attr("enctype", val);
    }

    public self_t method(Object val) {
        return attr("method", val);
    }

    /**
     * The name attribute represents the form's name within the forms collection. The value must not
     * be the empty string, and the value must be unique amongst the form elements in the forms
     * collection that it is in, if any.
     */
    public self_t name(Object val) {
        return attr("name", val);
    }

    public self_t novalidate(Object val) {
        return attr("novalidate", val);
    }

    public self_t target(Object val) {
        return attr("target", val);
    }

}
