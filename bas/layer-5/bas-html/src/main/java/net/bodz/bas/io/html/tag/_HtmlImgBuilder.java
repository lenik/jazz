package net.bodz.bas.io.html.tag;

import net.bodz.bas.io.html.DecoratedHtmlTagBuilder;
import net.bodz.bas.io.html.IHtmlTagBuilder;
import net.bodz.bas.io.xml.IXmlTagBuilder;

/**
  * The img element supports dimension attributes. 
  */
@SuppressWarnings("unchecked")
class _HtmlImgBuilder<self_t extends IHtmlTagBuilder<?>>
        extends DecoratedHtmlTagBuilder<self_t> {

    public _HtmlImgBuilder(IXmlTagBuilder o) {
        super(o);
    }

    /**
      * Another example use is when an image such as a spacer.gif is used to aid positioning of content. The alt attribute is empty as the image has no meaning. 
      */
    public self_t alt(String val) {
        attr("alt", val);
        return (self_t) this;
    }

    /**
      * The src attribute must be present, and must contain a valid non-empty URL potentially surrounded by spaces referencing a non-interactive, optionally animated, image resource that is neither paged nor scripted. 
      */
    public self_t src(String val) {
        attr("src", val);
        return (self_t) this;
    }

    /**
      * The crossorigin attribute is a CORS settings attribute. Its purpose is to allow images from third-party sites that allow cross-origin access to be used with canvas. 
      */
    public self_t crossorigin(String val) {
        attr("crossorigin", val);
        return (self_t) this;
    }

    /**
      * The usemap attribute, if present, can indicate that the image has an associated image map. 
      */
    public self_t usemap(String val) {
        attr("usemap", val);
        return (self_t) this;
    }

    /**
      * The ismap attribute is a boolean attribute. The attribute must not be specified on an element that does not have an ancestor a element with an href attribute. 
      */
    public self_t ismap(String val) {
        attr("ismap", val);
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
