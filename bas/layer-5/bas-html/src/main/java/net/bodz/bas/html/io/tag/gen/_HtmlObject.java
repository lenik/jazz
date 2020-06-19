package net.bodz.bas.html.io.tag.gen;

import net.bodz.bas.html.io.AbstractRecHtmlOut;
import net.bodz.bas.html.io.HtmlDoc;

/**
 * The object element supports dimension attributes.
 */
public class _HtmlObject<self_t extends AbstractRecHtmlOut<self_t>>
        extends AbstractRecHtmlOut<self_t> {

    public _HtmlObject(HtmlDoc doc) {
        super(doc);
    }

    /**
     * If the URL of the given resource is not about:blank, the element's nested browsing context
     * must then be navigated<!--DONAV object--> to that resource, with replacement enabled, and
     * with the object element's document's browsing context as the source browsing context. (The
     * data attribute of the object element doesn't get updated if the browsing context gets further
     * navigated to other locations.)
     */
    public self_t data(Object val) {
        return attr("data", val);
    }

    /**
     * The type attribute, if present, specifies the type of the resource. If present, the attribute
     * must be a valid MIME type.
     */
    public self_t type(Object val) {
        return attr("type", val);
    }

    /**
     * The typemustmatch attribute must not be specified unless both the data attribute and the type
     * attribute are present.
     */
    public self_t typemustmatch(Object val) {
        return attr("typemustmatch", val);
    }

    /**
     * The name attribute, if present, must be a valid browsing context name. The given value is
     * used to name the nested browsing context, if applicable.
     */
    public self_t name(Object val) {
        return attr("name", val);
    }

    /**
     * The usemap attribute, if present while the object element represents an image, can indicate
     * that the object has an associated image map. The attribute must be ignored if the object
     * element doesn't represent an image.
     */
    public self_t usemap(Object val) {
        return attr("usemap", val);
    }

    /**
     * The form attribute is used to explicitly associate the object element with its form owner.
     */
    public self_t form(Object val) {
        return attr("form", val);
    }

    public self_t width(Object val) {
        return attr("width", val);
    }

    public self_t height(Object val) {
        return attr("height", val);
    }

}
