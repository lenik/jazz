package net.bodz.bas.io.html.tag;

import net.bodz.bas.io.html.DecoratedHtmlTagBuilder;
import net.bodz.bas.io.html.IHtmlTagBuilder;
import net.bodz.bas.io.xml.IXmlTagBuilder;

/**
  * The audio element is a media element whose media data is ostensibly audio data. 
  */
@SuppressWarnings("unchecked")
class _HtmlAudioBuilder<self_t extends IHtmlTagBuilder<?>>
        extends DecoratedHtmlTagBuilder<self_t> {

    public _HtmlAudioBuilder(IXmlTagBuilder o) {
        super(o);
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
