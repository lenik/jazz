package net.bodz.bas.io.html.tag;

import net.bodz.bas.io.html.DecoratedHtmlTagBuilder;
import net.bodz.bas.io.xml.IXmlTagBuilder;

/**
  * The img element supports dimension attributes. 
  */
public class HtmlImgBuilder
        extends DecoratedHtmlTagBuilder<HtmlImgBuilder> {

    public HtmlImgBuilder(IXmlTagBuilder o) {
        super(o);
    }

    /**
      * Another example use is when an image such as a spacer.gif is used to aid positioning of content. The alt attribute is empty as the image has no meaning. 
      */
    public HtmlImgBuilder alt(String val) {
        attr("alt", val);
        return this;
    }

    /**
      * The src attribute must be present, and must contain a valid non-empty URL potentially surrounded by spaces referencing a non-interactive, optionally animated, image resource that is neither paged nor scripted. 
      */
    public HtmlImgBuilder src(String val) {
        attr("src", val);
        return this;
    }

    /**
      * The crossorigin attribute is a CORS settings attribute. Its purpose is to allow images from third-party sites that allow cross-origin access to be used with canvas. 
      */
    public HtmlImgBuilder crossorigin(String val) {
        attr("crossorigin", val);
        return this;
    }

    /**
      * The usemap attribute, if present, can indicate that the image has an associated image map. 
      */
    public HtmlImgBuilder usemap(String val) {
        attr("usemap", val);
        return this;
    }

    /**
      * The ismap attribute is a boolean attribute. The attribute must not be specified on an element that does not have an ancestor a element with an href attribute. 
      */
    public HtmlImgBuilder ismap(String val) {
        attr("ismap", val);
        return this;
    }

    public HtmlImgBuilder width(String val) {
        attr("width", val);
        return this;
    }

    public HtmlImgBuilder height(String val) {
        attr("height", val);
        return this;
    }

}
