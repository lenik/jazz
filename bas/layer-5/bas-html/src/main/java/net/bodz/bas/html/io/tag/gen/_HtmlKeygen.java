package net.bodz.bas.html.io.tag.gen;

import net.bodz.bas.html.io.HtmlDoc;
import net.bodz.bas.html.io.AbstractRecHtmlOut;

/**
 * Constraint validation: The keygen element is barred from constraint validation.
 */
public class _HtmlKeygen<self_t extends AbstractRecHtmlOut<self_t>>
        extends AbstractRecHtmlOut<self_t> {

    public _HtmlKeygen(HtmlDoc doc) {
        super(doc);
    }

    public self_t autofocus(Object val) {
        return attr("autofocus", val);
    }

    /**
     * The challenge attribute may be specified. Its value will be packaged with the submitted key.
     */
    public self_t challenge(Object val) {
        return attr("challenge", val);
    }

    public self_t disabled(Object val) {
        return attr("disabled", val);
    }

    /**
     * The form attribute is used to explicitly associate the keygen element with its form owner.
     * The name attribute represents the element's name. The disabled attribute is used to make the
     * control non-interactive and to prevent its value from being submitted. The autofocus
     * attribute controls focus.
     */
    public self_t form(Object val) {
        return attr("form", val);
    }

    /**
     * The keytype attribute is an enumerated attribute. The following table lists the keywords and
     * states for the attribute — the keywords in the left column map to the states listed in the
     * cell in the second column on the same row as the keyword. User agents are not required to
     * support these values, and must only recognize values whose corresponding algorithms they
     * support.
     */
    public self_t keytype(Object val) {
        return attr("keytype", val);
    }

    public self_t name(Object val) {
        return attr("name", val);
    }

}
