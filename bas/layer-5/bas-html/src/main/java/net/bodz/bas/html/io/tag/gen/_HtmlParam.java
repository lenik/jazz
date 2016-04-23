package net.bodz.bas.html.io.tag.gen;

import net.bodz.bas.html.io.HtmlDoc;
import net.bodz.bas.html.io.AbstractRecHtmlOut;

/**
 * <!--TOPIC:HTML-->The param element defines parameters for plugins invoked by object elements. It
 * does not represent anything on its own.
 */
public class _HtmlParam<self_t extends AbstractRecHtmlOut<self_t>>
        extends AbstractRecHtmlOut<self_t> {

    public _HtmlParam(HtmlDoc doc) {
        super(doc);
    }

    /**
     * The name attribute gives the name of the parameter.
     */
    public self_t name(Object val) {
        return attr("name", val);
    }

    /**
     * The value attribute gives the value of the parameter.
     */
    public self_t value(Object val) {
        return attr("value", val);
    }

}
