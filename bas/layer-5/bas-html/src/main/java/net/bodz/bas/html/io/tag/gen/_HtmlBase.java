package net.bodz.bas.html.io.tag.gen;

import net.bodz.bas.html.io.HtmlDoc;
import net.bodz.bas.html.io.RecHtmlOut;

/**
 * <!--TOPIC:HTML-->The base element allows authors to specify the document base URL for the
 * purposes of resolving relative URLs, and the name of the default browsing context for the
 * purposes of following hyperlinks. The element does not represent any content beyond this
 * information.
 */
public class _HtmlBase<self_t extends RecHtmlOut<self_t>>
        extends RecHtmlOut<self_t> {

    public _HtmlBase(HtmlDoc doc) {
        super(doc);
    }

    /**
     * The href content attribute, if specified, must contain a valid URL potentially surrounded by
     * spaces.
     */
    public self_t href(Object val) {
        return attr("href", val);
    }

    /**
     * The target attribute, if specified, must contain a valid browsing context name or keyword,
     * which specifies which browsing context is to be used as the default when hyperlinks and forms
     * in the Document cause navigation.
     */
    public self_t target(Object val) {
        return attr("target", val);
    }

}
