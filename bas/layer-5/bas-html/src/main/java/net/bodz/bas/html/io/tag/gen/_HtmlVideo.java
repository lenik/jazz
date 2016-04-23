package net.bodz.bas.html.io.tag.gen;

import net.bodz.bas.html.io.HtmlDoc;
import net.bodz.bas.html.io.AbstractRecHtmlOut;

/**
 * The video element supports dimension attributes.
 */
public class _HtmlVideo<self_t extends AbstractRecHtmlOut<self_t>>
        extends AbstractRecHtmlOut<self_t> {

    public _HtmlVideo(HtmlDoc doc) {
        super(doc);
    }

    public self_t src(Object val) {
        return attr("src", val);
    }

    public self_t crossorigin(Object val) {
        return attr("crossorigin", val);
    }

    /**
     * The poster attribute gives the address of an image file that the user agent can show while no
     * video data is available. The attribute, if present, must contain a valid non-empty URL
     * potentially surrounded by spaces.
     */
    public self_t poster(Object val) {
        return attr("poster", val);
    }

    public self_t preload(Object val) {
        return attr("preload", val);
    }

    public self_t autoplay(Object val) {
        return attr("autoplay", val);
    }

    public self_t mediagroup(Object val) {
        return attr("mediagroup", val);
    }

    public self_t loop(Object val) {
        return attr("loop", val);
    }

    public self_t muted(Object val) {
        return attr("muted", val);
    }

    public self_t controls(Object val) {
        return attr("controls", val);
    }

    public self_t width(Object val) {
        return attr("width", val);
    }

    public self_t height(Object val) {
        return attr("height", val);
    }

}
