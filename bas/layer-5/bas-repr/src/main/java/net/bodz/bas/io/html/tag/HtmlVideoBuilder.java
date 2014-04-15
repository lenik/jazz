package net.bodz.bas.io.html.tag;

import net.bodz.bas.io.html.DecoratedHtmlTagBuilder;
import net.bodz.bas.io.xml.IXmlTagBuilder;

/**
  * The video element supports dimension attributes. 
  */
public class HtmlVideoBuilder
        extends DecoratedHtmlTagBuilder<HtmlVideoBuilder> {

    public HtmlVideoBuilder(IXmlTagBuilder o) {
        super(o);
    }

    public HtmlVideoBuilder src(String val) {
        attr("src", val);
        return this;
    }

    public HtmlVideoBuilder crossorigin(String val) {
        attr("crossorigin", val);
        return this;
    }

    /**
      * The poster attribute gives the address of an image file that the user agent can show while no video data is available. The attribute, if present, must contain a valid non-empty URL potentially surrounded by spaces. 
      */
    public HtmlVideoBuilder poster(String val) {
        attr("poster", val);
        return this;
    }

    public HtmlVideoBuilder preload(String val) {
        attr("preload", val);
        return this;
    }

    public HtmlVideoBuilder autoplay(String val) {
        attr("autoplay", val);
        return this;
    }

    public HtmlVideoBuilder mediagroup(String val) {
        attr("mediagroup", val);
        return this;
    }

    public HtmlVideoBuilder loop(String val) {
        attr("loop", val);
        return this;
    }

    public HtmlVideoBuilder muted(String val) {
        attr("muted", val);
        return this;
    }

    public HtmlVideoBuilder controls(String val) {
        attr("controls", val);
        return this;
    }

    public HtmlVideoBuilder width(String val) {
        attr("width", val);
        return this;
    }

    public HtmlVideoBuilder height(String val) {
        attr("height", val);
        return this;
    }

}
