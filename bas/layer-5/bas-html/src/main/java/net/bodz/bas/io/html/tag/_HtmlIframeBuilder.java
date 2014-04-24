package net.bodz.bas.io.html.tag;

import net.bodz.bas.io.html.DecoratedHtmlTagBuilder;
import net.bodz.bas.io.html.IHtmlTagBuilder;
import net.bodz.bas.io.xml.IXmlTagBuilder;

/**
  * The iframe element must be empty in XML documents. 
  */
@SuppressWarnings("unchecked")
class _HtmlIframeBuilder<self_t extends IHtmlTagBuilder<?>>
        extends DecoratedHtmlTagBuilder<self_t> {

    public _HtmlIframeBuilder(IXmlTagBuilder o) {
        super(o);
    }

    /**
      * The src attribute gives the address of a page that the nested browsing context is to contain. The attribute, if present, must be a valid non-empty URL potentially surrounded by spaces. 
      */
    public self_t src(String val) {
        attr("src", val);
        return (self_t) this;
    }

    /**
      * The srcdoc attribute gives the content of the page that the nested browsing context is to contain. The value of the attribute is the source of an iframe srcdoc document. 
      */
    public self_t srcdoc(String val) {
        attr("srcdoc", val);
        return (self_t) this;
    }

    /**
      * <!-- NAME -->The name attribute, if present, must be a valid browsing context name. The given value is used to name the nested browsing context. When the browsing context is created, if the attribute is present, the browsing context name must be set to the value of this attribute; otherwise, the browsing context name must be set to the empty string. 
      */
    public self_t name(String val) {
        attr("name", val);
        return (self_t) this;
    }

    /**
      * <!-- SANDBOX -->The sandbox attribute, when specified, enables a set of extra restrictions on any content hosted by the iframe. Its value must be an unordered set of unique space-separated tokens that are ASCII case-insensitive. The allowed values are allow-forms, allow-pointer-lock, allow-popups, allow-same-origin, allow-scripts, and allow-top-navigation. 
      */
    public self_t sandbox(String val) {
        attr("sandbox", val);
        return (self_t) this;
    }

    /**
      * <!-- SEAMLESS -->The seamless attribute is a boolean attribute. When specified, it indicates that the iframe element's browsing context is to be rendered in a manner that makes it appear to be part of the containing document (seamlessly included in the parent document). 
      */
    public self_t seamless(String val) {
        attr("seamless", val);
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
