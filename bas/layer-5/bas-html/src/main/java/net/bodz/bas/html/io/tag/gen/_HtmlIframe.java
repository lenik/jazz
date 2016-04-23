package net.bodz.bas.html.io.tag.gen;

import net.bodz.bas.html.io.HtmlDoc;
import net.bodz.bas.html.io.AbstractRecHtmlOut;

/**
 * The iframe element must be empty in XML documents.
 */
public class _HtmlIframe<self_t extends AbstractRecHtmlOut<self_t>>
        extends AbstractRecHtmlOut<self_t> {

    public _HtmlIframe(HtmlDoc doc) {
        super(doc);
    }

    /**
     * The src attribute gives the address of a page that the nested browsing context is to contain.
     * The attribute, if present, must be a valid non-empty URL potentially surrounded by spaces.
     */
    public self_t src(Object val) {
        return attr("src", val);
    }

    /**
     * The srcdoc attribute gives the content of the page that the nested browsing context is to
     * contain. The value of the attribute is the source of an iframe srcdoc document.
     */
    public self_t srcdoc(Object val) {
        return attr("srcdoc", val);
    }

    /**
     * <!-- NAME -->The name attribute, if present, must be a valid browsing context name. The given
     * value is used to name the nested browsing context. When the browsing context is created, if
     * the attribute is present, the browsing context name must be set to the value of this
     * attribute; otherwise, the browsing context name must be set to the empty string.
     */
    public self_t name(Object val) {
        return attr("name", val);
    }

    /**
     * <!-- SANDBOX -->The sandbox attribute, when specified, enables a set of extra restrictions on
     * any content hosted by the iframe. Its value must be an unordered set of unique
     * space-separated tokens that are ASCII case-insensitive. The allowed values are allow-forms,
     * allow-pointer-lock, allow-popups, allow-same-origin, allow-scripts, and allow-top-navigation.
     */
    public self_t sandbox(Object val) {
        return attr("sandbox", val);
    }

    public self_t width(Object val) {
        return attr("width", val);
    }

    public self_t height(Object val) {
        return attr("height", val);
    }

}
