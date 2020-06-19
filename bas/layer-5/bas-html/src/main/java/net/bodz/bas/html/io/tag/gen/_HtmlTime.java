package net.bodz.bas.html.io.tag.gen;

import net.bodz.bas.html.io.AbstractRecHtmlOut;
import net.bodz.bas.html.io.HtmlDoc;

/**
 * The time element can be used to encode dates, for example in microformats. The following shows a
 * hypothetical way of encoding an event using a variant on hCalendar that uses the time element:
 */
public class _HtmlTime<self_t extends AbstractRecHtmlOut<self_t>>
        extends AbstractRecHtmlOut<self_t> {

    public _HtmlTime(HtmlDoc doc) {
        super(doc);
    }

    /**
     * The datetime attribute may be present. If present, its value must be a representation of the
     * element's contents in a machine-readable format.
     */
    public self_t datetime(Object val) {
        return attr("datetime", val);
    }

}
