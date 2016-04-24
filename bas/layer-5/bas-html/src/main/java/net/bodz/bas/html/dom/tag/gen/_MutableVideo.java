package net.bodz.bas.html.dom.tag.gen;

import net.bodz.bas.html.dom.IHtmlTag;
import net.bodz.bas.html.dom.MutableHtmlTag;

/**
  * The video element supports dimension attributes. 
  */
@SuppressWarnings("unchecked")
public class _MutableVideo<self_t extends IHtmlTag>
        extends MutableHtmlTag<self_t> {

    public _MutableVideo(IHtmlTag parent) {
        super(parent, "video");
    }

    public self_t src(Object val) {
        attr("src", val);
        return (self_t) this;
    }

    public self_t crossorigin(Object val) {
        attr("crossorigin", val);
        return (self_t) this;
    }

    /**
      * The poster attribute gives the address of an image file that the user agent can show while no video data is available. The attribute, if present, must contain a valid non-empty URL potentially surrounded by spaces. 
      */
    public self_t poster(Object val) {
        attr("poster", val);
        return (self_t) this;
    }

    public self_t preload(Object val) {
        attr("preload", val);
        return (self_t) this;
    }

    public self_t autoplay(Object val) {
        attr("autoplay", val);
        return (self_t) this;
    }

    public self_t mediagroup(Object val) {
        attr("mediagroup", val);
        return (self_t) this;
    }

    public self_t loop(Object val) {
        attr("loop", val);
        return (self_t) this;
    }

    public self_t muted(Object val) {
        attr("muted", val);
        return (self_t) this;
    }

    public self_t controls(Object val) {
        attr("controls", val);
        return (self_t) this;
    }

    public self_t width(Object val) {
        attr("width", val);
        return (self_t) this;
    }

    public self_t height(Object val) {
        attr("height", val);
        return (self_t) this;
    }

}
