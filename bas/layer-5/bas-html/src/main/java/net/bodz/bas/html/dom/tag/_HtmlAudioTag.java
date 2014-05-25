package net.bodz.bas.html.dom.tag;

import net.bodz.bas.html.dom.AbstractHtmlTag;
import net.bodz.bas.html.dom.IHtmlTag;

/**
  * The audio element is a media element whose media data is ostensibly audio data. 
  */
@SuppressWarnings("unchecked")
class _HtmlAudioTag<self_t extends IHtmlTag>
        extends AbstractHtmlTag<self_t> {

    public _HtmlAudioTag(IHtmlTag parent, String tagName) {
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

}