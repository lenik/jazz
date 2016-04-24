package net.bodz.bas.html.dom.tag.gen;

import net.bodz.bas.html.dom.IHtmlTag;
import net.bodz.bas.html.dom.MutableHtmlTag;

/**
  * The figure element is used to group the Bar Chart image and data table. The figcaption element provides a caption for the grouped content. 
  */
@SuppressWarnings("unchecked")
public class _MutableImg<self_t extends IHtmlTag>
        extends MutableHtmlTag<self_t> {

    public _MutableImg(IHtmlTag parent) {
        super(parent, "img");
    }

    /**
      * Another example use is when an image such as a spacer.gif is used to aid positioning of content. The alt attribute is empty as the image has no meaning. 
      */
    public self_t alt(Object val) {
        attr("alt", val);
        return (self_t) this;
    }

    /**
      * The src attribute must be present, and must contain a valid non-empty URL potentially surrounded by spaces referencing a non-interactive, optionally animated, image resource that is neither paged nor scripted. 
      */
    public self_t src(Object val) {
        attr("src", val);
        return (self_t) this;
    }

    /**
      * The crossorigin attribute is a CORS settings attribute. Its purpose is to allow images from third-party sites that allow cross-origin access to be used with canvas. 
      */
    public self_t crossorigin(Object val) {
        attr("crossorigin", val);
        return (self_t) this;
    }

    /**
      * The usemap attribute, if present, can indicate that the image has an associated image map. 
      */
    public self_t usemap(Object val) {
        attr("usemap", val);
        return (self_t) this;
    }

    /**
      * The ismap attribute is a boolean attribute. The attribute must not be specified on an element that does not have an ancestor a element with an href attribute. 
      */
    public self_t ismap(Object val) {
        attr("ismap", val);
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
