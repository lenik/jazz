package net.bodz.bas.io.html.tag;

import net.bodz.bas.io.html.DecoratedHtmlTagBuilder;
import net.bodz.bas.io.html.IHtmlTagBuilder;
import net.bodz.bas.io.xml.IXmlTagBuilder;

/**
  * <!-- concept-uu -->The area element also supports the URLUtils interface. [URL] 
  */
@SuppressWarnings("unchecked")
class _HtmlAreaBuilder<self_t extends IHtmlTagBuilder<?>>
        extends DecoratedHtmlTagBuilder<self_t> {

    public _HtmlAreaBuilder(IXmlTagBuilder o) {
        super(o);
    }

    /**
      * If the area element has an href attribute, then the area element represents a hyperlink. In this case, the alt attribute must be present. It specifies the text of the hyperlink. Its value must be text that, when presented with the texts specified for the other hyperlinks of the image map, and with the alternative text of the image, but without the image itself, provides the user with the same kind of choice as the hyperlink would when used without its text but with its shape applied to the image. The alt attribute may be left blank if there is another area element in the same image map that points to the same resource and has a non-blank alt attribute. 
      */
    public self_t alt(String val) {
        attr("alt", val);
        return (self_t) this;
    }

    /**
      * The coords attribute must, if specified, contain a valid list of integers. This attribute gives the coordinates for the shape described by the shape attribute. The processing for this attribute is described as part of the image map processing model. 
      */
    public self_t coords(String val) {
        attr("coords", val);
        return (self_t) this;
    }

    /**
      * The shape attribute is an enumerated attribute. The following table lists the keywords defined for this attribute. The states given in the first cell of the rows with keywords give the states to which those keywords map. Some of the keywords are non-conforming, as noted in the last column. 
      */
    public self_t shape(String val) {
        attr("shape", val);
        return (self_t) this;
    }

    public self_t href(String val) {
        attr("href", val);
        return (self_t) this;
    }

    public self_t target(String val) {
        attr("target", val);
        return (self_t) this;
    }

    public self_t download(String val) {
        attr("download", val);
        return (self_t) this;
    }

    public self_t rel(String val) {
        attr("rel", val);
        return (self_t) this;
    }

    public self_t hreflang(String val) {
        attr("hreflang", val);
        return (self_t) this;
    }

    public self_t type(String val) {
        attr("type", val);
        return (self_t) this;
    }

}
