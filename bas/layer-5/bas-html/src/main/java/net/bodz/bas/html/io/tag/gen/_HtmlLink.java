package net.bodz.bas.html.io.tag.gen;

import net.bodz.bas.html.io.HtmlDoc;
import net.bodz.bas.html.io.RecHtmlOut;

/**
 * <!--TOPIC:HTML-->The link element allows authors to link their document to other resources.
 */
public class _HtmlLink<self_t extends RecHtmlOut<self_t>>
        extends RecHtmlOut<self_t> {

    public _HtmlLink(HtmlDoc doc) {
        super(doc);
    }

    public self_t href(Object val) {
        return attr("href", val);
    }

    /**
     * The crossorigin attribute is a CORS settings attribute. It is intended for use with external
     * resource links.
     */
    public self_t crossorigin(Object val) {
        return attr("crossorigin", val);
    }

    public self_t rel(Object val) {
        return attr("rel", val);
    }

    /**
     * The media attribute says which media the resource applies to. The value must be a valid media
     * query.
     */
    public self_t media(Object val) {
        return attr("media", val);
    }

    /**
     * The hreflang attribute on the link element has the same semantics as the hreflang attribute
     * on a and area elements.
     */
    public self_t hreflang(Object val) {
        return attr("hreflang", val);
    }

    /**
     * The type attribute gives the MIME type of the linked resource. It is purely advisory. The
     * value must be a valid MIME type.
     */
    public self_t type(Object val) {
        return attr("type", val);
    }

    /**
     * The sizes attribute is used with the icon link type. The attribute must not be specified on
     * link elements that do not have a rel attribute that specifies the icon keyword.
     */
    public self_t sizes(Object val) {
        return attr("sizes", val);
    }

}
