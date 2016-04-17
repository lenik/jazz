package net.bodz.bas.html.io.tag.gen;

import net.bodz.bas.html.io.HtmlDoc;
import net.bodz.bas.html.io.RecHtmlOut;

/**
 * The audio element is a media element whose media data is ostensibly audio data.
 */
public class _HtmlAudio<self_t extends RecHtmlOut<self_t>>
        extends RecHtmlOut<self_t> {

    public _HtmlAudio(HtmlDoc doc) {
        super(doc);
    }

    public self_t src(Object val) {
        return attr("src", val);
    }

    public self_t crossorigin(Object val) {
        return attr("crossorigin", val);
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

}
