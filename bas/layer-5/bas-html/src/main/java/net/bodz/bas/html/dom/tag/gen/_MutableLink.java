package net.bodz.bas.html.dom.tag.gen;

import net.bodz.bas.html.dom.IHtmlTag;
import net.bodz.bas.html.dom.MutableHtmlTag;

/**
  * <!--TOPIC:HTML-->The link element allows authors to link their document to other resources. 
  */
@SuppressWarnings("unchecked")
public class _MutableLink<self_t extends IHtmlTag>
        extends MutableHtmlTag<self_t> {

    public _MutableLink(IHtmlTag parent, String tagName) {
        super(parent, tagName);
    }

    public self_t href(Object val) {
        attr("href", val);
        return (self_t) this;
    }

    /**
      * The crossorigin attribute is a CORS settings attribute. It is intended for use with external resource links. 
      */
    public self_t crossorigin(Object val) {
        attr("crossorigin", val);
        return (self_t) this;
    }

    public self_t rel(Object val) {
        attr("rel", val);
        return (self_t) this;
    }

    /**
      * The media attribute says which media the resource applies to. The value must be a valid media query. 
      */
    public self_t media(Object val) {
        attr("media", val);
        return (self_t) this;
    }

    /**
      * The hreflang attribute on the link element has the same semantics as the hreflang attribute on a and area elements. 
      */
    public self_t hreflang(Object val) {
        attr("hreflang", val);
        return (self_t) this;
    }

    /**
      * The type attribute gives the MIME type of the linked resource. It is purely advisory. The value must be a valid MIME type. 
      */
    public self_t type(Object val) {
        attr("type", val);
        return (self_t) this;
    }

    /**
      * The sizes attribute is used with the icon link type. The attribute must not be specified on link elements that do not have a rel attribute that specifies the icon keyword. 
      */
    public self_t sizes(Object val) {
        attr("sizes", val);
        return (self_t) this;
    }

}
