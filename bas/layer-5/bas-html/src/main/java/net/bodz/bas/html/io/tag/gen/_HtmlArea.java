package net.bodz.bas.html.io.tag.gen;

import net.bodz.bas.html.io.AbstractRecHtmlOut;
import net.bodz.bas.html.io.HtmlDoc;

/**
 * <!-- concept-uu -->The area element also supports the URLUtils interface. [URL]
 */
public class _HtmlArea<self_t extends AbstractRecHtmlOut<self_t>>
        extends AbstractRecHtmlOut<self_t> {

    public _HtmlArea(HtmlDoc doc) {
        super(doc);
    }

    /**
     * If the area element has an href attribute, then the area element represents a hyperlink. In
     * this case, the alt attribute must be present. It specifies the text of the hyperlink. Its
     * value must be text that, when presented with the texts specified for the other hyperlinks of
     * the image map, and with the alternative text of the image, but without the image itself,
     * provides the user with the same kind of choice as the hyperlink would when used without its
     * text but with its shape applied to the image. The alt attribute may be left blank if there is
     * another area element in the same image map that points to the same resource and has a
     * non-blank alt attribute.
     */
    public self_t alt(Object val) {
        return attr("alt", val);
    }

    /**
     * The coords attribute must, if specified, contain a valid list of integers. This attribute
     * gives the coordinates for the shape described by the shape attribute. The processing for this
     * attribute is described as part of the image map processing model.
     */
    public self_t coords(Object val) {
        return attr("coords", val);
    }

    public self_t download(Object val) {
        return attr("download", val);
    }

    public self_t href(Object val) {
        return attr("href", val);
    }

    public self_t hreflang(Object val) {
        return attr("hreflang", val);
    }

    public self_t rel(Object val) {
        return attr("rel", val);
    }

    /**
     * The shape attribute is an enumerated attribute. The following table lists the keywords
     * defined for this attribute. The states given in the first cell of the rows with keywords give
     * the states to which those keywords map. Some of the keywords are non-conforming, as noted in
     * the last column.
     */
    public self_t shape(Object val) {
        return attr("shape", val);
    }

    public self_t target(Object val) {
        return attr("target", val);
    }

    public self_t type(Object val) {
        return attr("type", val);
    }

}
