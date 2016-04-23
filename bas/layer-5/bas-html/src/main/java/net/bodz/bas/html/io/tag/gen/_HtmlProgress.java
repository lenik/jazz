package net.bodz.bas.html.io.tag.gen;

import net.bodz.bas.html.io.HtmlDoc;
import net.bodz.bas.html.io.AbstractRecHtmlOut;

/**
 * The progress element is the wrong element to use for something that is just a gauge, as opposed
 * to task progress. For instance, indicating disk space usage using progress would be
 * inappropriate. Instead, the meter element is available for such use cases.
 */
public class _HtmlProgress<self_t extends AbstractRecHtmlOut<self_t>>
        extends AbstractRecHtmlOut<self_t> {

    public _HtmlProgress(HtmlDoc doc) {
        super(doc);
    }

    /**
     * The value and max attributes, when present, must have values that are valid floating-point
     * numbers. The value attribute, if present, must have a value equal to or greater than zero,
     * and less than or equal to the value of the max attribute, if present, or 1.0, otherwise. The
     * max attribute, if present, must have a value greater than zero.
     */
    public self_t value(Object val) {
        return attr("value", val);
    }

    public self_t max(Object val) {
        return attr("max", val);
    }

}
