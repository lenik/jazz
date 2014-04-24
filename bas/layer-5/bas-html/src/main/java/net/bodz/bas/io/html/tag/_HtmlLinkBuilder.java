package net.bodz.bas.io.html.tag;

import net.bodz.bas.io.html.DecoratedHtmlTagBuilder;
import net.bodz.bas.io.html.IHtmlTagBuilder;
import net.bodz.bas.io.xml.IXmlTagBuilder;

/**
  * <!--TOPIC:HTML-->The link element allows authors to link their document to other resources. 
  */
@SuppressWarnings("unchecked")
class _HtmlLinkBuilder<self_t extends IHtmlTagBuilder<?>>
        extends DecoratedHtmlTagBuilder<self_t> {

    public _HtmlLinkBuilder(IXmlTagBuilder o) {
        super(o);
    }

    public self_t href(String val) {
        attr("href", val);
        return (self_t) this;
    }

    /**
      * The crossorigin attribute is a CORS settings attribute. It is intended for use with external resource links. 
      */
    public self_t crossorigin(String val) {
        attr("crossorigin", val);
        return (self_t) this;
    }

    public self_t rel(String val) {
        attr("rel", val);
        return (self_t) this;
    }

    /**
      * The media attribute says which media the resource applies to. The value must be a valid media query. 
      */
    public self_t media(String val) {
        attr("media", val);
        return (self_t) this;
    }

    /**
      * The hreflang attribute on the link element has the same semantics as the hreflang attribute on a and area elements. 
      */
    public self_t hreflang(String val) {
        attr("hreflang", val);
        return (self_t) this;
    }

    /**
      * The type attribute gives the MIME type of the linked resource. It is purely advisory. The value must be a valid MIME type. 
      */
    public self_t type(String val) {
        attr("type", val);
        return (self_t) this;
    }

    /**
      * The sizes attribute is used with the icon link type. The attribute must not be specified on link elements that do not have a rel attribute that specifies the icon keyword. 
      */
    public self_t sizes(String val) {
        attr("sizes", val);
        return (self_t) this;
    }

}
