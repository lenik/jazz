package net.bodz.bas.html.io.tag.gen;

import net.bodz.bas.html.io.HtmlDoc;
import net.bodz.bas.html.io.RecHtmlOut;

/**
 * <!--TOPIC:HTML-->The style element allows authors to embed style information in their documents.
 * The style element is one of several inputs to the styling processing model. The element does not
 * represent content for the user.
 */
public class _HtmlStyle<self_t extends RecHtmlOut<self_t>>
        extends RecHtmlOut<self_t> {

    public _HtmlStyle(HtmlDoc doc) {
        super(doc);
    }

    /**
     * The media attribute says which media the styles apply to. The value must be a valid media
     * query. The user agent must apply the styles when the media attribute's value matches the
     * environment and the other relevant conditions apply, and must not apply them otherwise.<!--
     * note similar text in section -->
     */
    public self_t media(Object val) {
        return attr("media", val);
    }

    /**
     * The type attribute gives the styling language. If the attribute is present, its value must be
     * a valid MIME type that designates a styling language. The charset parameter must not be
     * specified. The default value for the type attribute, which is used if the attribute is
     * absent, is "text/css". [RFC2318]
     */
    public self_t type(Object val) {
        return attr("type", val);
    }

}
