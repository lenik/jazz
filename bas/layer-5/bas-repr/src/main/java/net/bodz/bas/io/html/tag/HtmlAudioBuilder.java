package net.bodz.bas.io.html.tag;

import net.bodz.bas.io.html.DecoratedHtmlTagBuilder;
import net.bodz.bas.io.xml.IXmlTagBuilder;

/**
  * The audio element is a media element whose media data is ostensibly audio data. 
  */
public class HtmlAudioBuilder
        extends DecoratedHtmlTagBuilder<HtmlAudioBuilder> {

    public HtmlAudioBuilder(IXmlTagBuilder o) {
        super(o);
    }

    public HtmlAudioBuilder src(String val) {
        attr("src", val);
        return this;
    }

    public HtmlAudioBuilder crossorigin(String val) {
        attr("crossorigin", val);
        return this;
    }

    public HtmlAudioBuilder preload(String val) {
        attr("preload", val);
        return this;
    }

    public HtmlAudioBuilder autoplay(String val) {
        attr("autoplay", val);
        return this;
    }

    public HtmlAudioBuilder mediagroup(String val) {
        attr("mediagroup", val);
        return this;
    }

    public HtmlAudioBuilder loop(String val) {
        attr("loop", val);
        return this;
    }

    public HtmlAudioBuilder muted(String val) {
        attr("muted", val);
        return this;
    }

    public HtmlAudioBuilder controls(String val) {
        attr("controls", val);
        return this;
    }

}
