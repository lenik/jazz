package net.bodz.bas.html.io.tag.gen;

import net.bodz.bas.html.io.HtmlDoc;
import net.bodz.bas.html.io.RecHtmlOut;

/**
 * <!--TOPIC:HTML-->The data element represents its contents, along with a machine-readable form of
 * those contents in the value attribute.
 */
public class _HtmlData<self_t extends RecHtmlOut<self_t>>
        extends RecHtmlOut<self_t> {

    public _HtmlData(HtmlDoc doc) {
        super(doc);
    }

    /**
     * The value attribute must be present. Its value must be a representation of the element's
     * contents in a machine-readable format.
     */
    public self_t value(Object val) {
        return attr("value", val);
    }

}
