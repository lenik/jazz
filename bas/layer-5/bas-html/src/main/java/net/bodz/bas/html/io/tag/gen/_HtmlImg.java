package net.bodz.bas.html.io.tag.gen;

import net.bodz.bas.html.io.HtmlDoc;
import net.bodz.bas.html.io.RecHtmlOut;

/**
 * The figure element is used to group the Bar Chart image and data table. The figcaption element
 * provides a caption for the grouped content.
 */
public class _HtmlImg<self_t extends RecHtmlOut<self_t>>
        extends RecHtmlOut<self_t> {

    public _HtmlImg(HtmlDoc doc) {
        super(doc);
    }

    /**
     * Another example use is when an image such as a spacer.gif is used to aid positioning of
     * content. The alt attribute is empty as the image has no meaning.
     */
    public self_t alt(Object val) {
        return attr("alt", val);
    }

    /**
     * The src attribute must be present, and must contain a valid non-empty URL potentially
     * surrounded by spaces referencing a non-interactive, optionally animated, image resource that
     * is neither paged nor scripted.
     */
    public self_t src(Object val) {
        return attr("src", val);
    }

    /**
     * The crossorigin attribute is a CORS settings attribute. Its purpose is to allow images from
     * third-party sites that allow cross-origin access to be used with canvas.
     */
    public self_t crossorigin(Object val) {
        return attr("crossorigin", val);
    }

    /**
     * The usemap attribute, if present, can indicate that the image has an associated image map.
     */
    public self_t usemap(Object val) {
        return attr("usemap", val);
    }

    /**
     * The ismap attribute is a boolean attribute. The attribute must not be specified on an element
     * that does not have an ancestor a element with an href attribute.
     */
    public self_t ismap(Object val) {
        return attr("ismap", val);
    }

    public self_t width(Object val) {
        return attr("width", val);
    }

    public self_t height(Object val) {
        return attr("height", val);
    }

}
