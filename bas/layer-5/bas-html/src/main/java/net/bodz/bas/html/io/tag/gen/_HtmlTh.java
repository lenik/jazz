package net.bodz.bas.html.io.tag.gen;

import net.bodz.bas.html.io.HtmlDoc;
import net.bodz.bas.html.io.RecHtmlOut;

/**
 * The tbody elements in this example identify the range of the row groups.
 */
public class _HtmlTh<self_t extends RecHtmlOut<self_t>>
        extends RecHtmlOut<self_t> {

    public _HtmlTh(HtmlDoc doc) {
        super(doc);
    }

    public self_t colspan(Object val) {
        return attr("colspan", val);
    }

    public self_t rowspan(Object val) {
        return attr("rowspan", val);
    }

    public self_t headers(Object val) {
        return attr("headers", val);
    }

    /**
     * The scope attribute's missing value default is the auto state.
     */
    public self_t scope(Object val) {
        return attr("scope", val);
    }

    public self_t abbr(Object val) {
        return attr("abbr", val);
    }

}
