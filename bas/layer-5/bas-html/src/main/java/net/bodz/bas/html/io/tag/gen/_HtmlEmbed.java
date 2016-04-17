package net.bodz.bas.html.io.tag.gen;

import net.bodz.bas.html.io.HtmlDoc;
import net.bodz.bas.html.io.RecHtmlOut;

/**
 * The embed element supports dimension attributes.
 */
public class _HtmlEmbed<self_t extends RecHtmlOut<self_t>>
        extends RecHtmlOut<self_t> {

    public _HtmlEmbed(HtmlDoc doc) {
        super(doc);
    }

    /**
     * Navigate<!--DONAV embed--> the nested browsing context to the fetched resource, with
     * replacement enabled, and with the embed element's document's browsing context as the source
     * browsing context. (The src attribute of the embed element doesn't get updated if the browsing
     * context gets further navigated to other locations.) The embed element now represents its
     * associated nested browsing context. Otherwise, find and instantiate an appropriate plugin
     * based on the content's type, and hand that plugin the content of the resource, replacing any
     * previously instantiated plugin for the element. The embed element now represents this plugin
     * instance.
     */
    public self_t src(Object val) {
        return attr("src", val);
    }

    /**
     * The type attribute, if present, gives the MIME type by which the plugin to instantiate is
     * selected. The value must be a valid MIME type. If both the type attribute and the src
     * attribute are present, then the type attribute must specify the same type as the explicit
     * Content-Type metadata of the resource given by the src attribute.
     */
    public self_t type(Object val) {
        return attr("type", val);
    }

    public self_t width(Object val) {
        return attr("width", val);
    }

    public self_t height(Object val) {
        return attr("height", val);
    }

}
