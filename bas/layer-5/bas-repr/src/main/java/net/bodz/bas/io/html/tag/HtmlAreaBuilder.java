package net.bodz.bas.io.html.tag;

import net.bodz.bas.io.html.DecoratedHtmlTagBuilder;
import net.bodz.bas.io.xml.IXmlTagBuilder;

/**
  * <!-- concept-uu -->The area element also supports the URLUtils interface. [URL] 
  */
public class HtmlAreaBuilder
        extends DecoratedHtmlTagBuilder<HtmlAreaBuilder> {

    public HtmlAreaBuilder(IXmlTagBuilder o) {
        super(o);
    }

    /**
      * If the area element has an href attribute, then the area element represents a hyperlink. In this case, the alt attribute must be present. It specifies the text of the hyperlink. Its value must be text that, when presented with the texts specified for the other hyperlinks of the image map, and with the alternative text of the image, but without the image itself, provides the user with the same kind of choice as the hyperlink would when used without its text but with its shape applied to the image. The alt attribute may be left blank if there is another area element in the same image map that points to the same resource and has a non-blank alt attribute. 
      */
    public HtmlAreaBuilder alt(String val) {
        attr("alt", val);
        return this;
    }

    /**
      * The coords attribute must, if specified, contain a valid list of integers. This attribute gives the coordinates for the shape described by the shape attribute. The processing for this attribute is described as part of the image map processing model. 
      */
    public HtmlAreaBuilder coords(String val) {
        attr("coords", val);
        return this;
    }

    /**
      * The shape attribute is an enumerated attribute. The following table lists the keywords defined for this attribute. The states given in the first cell of the rows with keywords give the states to which those keywords map. Some of the keywords are non-conforming, as noted in the last column. 
      */
    public HtmlAreaBuilder shape(String val) {
        attr("shape", val);
        return this;
    }

    public HtmlAreaBuilder href(String val) {
        attr("href", val);
        return this;
    }

    public HtmlAreaBuilder target(String val) {
        attr("target", val);
        return this;
    }

    public HtmlAreaBuilder download(String val) {
        attr("download", val);
        return this;
    }

    public HtmlAreaBuilder rel(String val) {
        attr("rel", val);
        return this;
    }

    public HtmlAreaBuilder hreflang(String val) {
        attr("hreflang", val);
        return this;
    }

    public HtmlAreaBuilder type(String val) {
        attr("type", val);
        return this;
    }

}
