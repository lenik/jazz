package net.bodz.bas.html.dom.tag.gen;

import net.bodz.bas.html.dom.IHtmlTag;
import net.bodz.bas.html.dom.MutableHtmlTag;

/**
  * The audio element is a media element whose media data is ostensibly audio data. 
  */
@SuppressWarnings("unchecked")
public class _MutableAudio<self_t extends IHtmlTag>
        extends MutableHtmlTag<self_t> {

    public _MutableAudio(IHtmlTag parent, String tagName) {
        super(parent, tagName);
    }

    public self_t src(Object val) {
        attr("src", val);
        return (self_t) this;
    }

    public self_t crossorigin(Object val) {
        attr("crossorigin", val);
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

}
