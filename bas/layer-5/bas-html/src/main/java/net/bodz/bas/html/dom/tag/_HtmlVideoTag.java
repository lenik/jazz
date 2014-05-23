package net.bodz.bas.html.dom.tag;

import net.bodz.bas.html.dom.AbstractHtmlTag;
import net.bodz.bas.html.dom.IHtmlTag;

/**
  * The video element supports dimension attributes. 
  */
@SuppressWarnings("unchecked")
class _HtmlVideoTag<self_t extends IHtmlTag>
        extends AbstractHtmlTag<self_t> {

    public _HtmlVideoTag(IHtmlTag parent, String tagName) {
        super(parent, tagName);
    }

    public self_t src(String val) {
        attr("src", val);
        return (self_t) this;
    }

    public self_t crossorigin(String val) {
        attr("crossorigin", val);
        return (self_t) this;
    }

    /**
      * The poster attribute gives the address of an image file that the user agent can show while no video data is available. The attribute, if present, must contain a valid non-empty URL potentially surrounded by spaces. 
      */
    public self_t poster(String val) {
        attr("poster", val);
        return (self_t) this;
    }

    public self_t preload(String val) {
        attr("preload", val);
        return (self_t) this;
    }

    public self_t autoplay(String val) {
        attr("autoplay", val);
        return (self_t) this;
    }

    public self_t mediagroup(String val) {
        attr("mediagroup", val);
        return (self_t) this;
    }

    public self_t loop(String val) {
        attr("loop", val);
        return (self_t) this;
    }

    public self_t muted(String val) {
        attr("muted", val);
        return (self_t) this;
    }

    public self_t controls(String val) {
        attr("controls", val);
        return (self_t) this;
    }

    public self_t width(String val) {
        attr("width", val);
        return (self_t) this;
    }

    public self_t height(String val) {
        attr("height", val);
        return (self_t) this;
    }

}
