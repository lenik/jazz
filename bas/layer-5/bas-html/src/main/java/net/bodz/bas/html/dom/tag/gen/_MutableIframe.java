package net.bodz.bas.html.dom.tag.gen;

import net.bodz.bas.html.dom.IHtmlTag;
import net.bodz.bas.html.dom.MutableHtmlTag;

/**
  * The iframe element must be empty in XML documents. 
  */
@SuppressWarnings("unchecked")
public class _MutableIframe<self_t extends IHtmlTag>
        extends MutableHtmlTag<self_t> {

    public _MutableIframe(IHtmlTag parent) {
        super(parent, "iframe");
    }

    /**
      * The src attribute gives the address of a page that the nested browsing context is to contain. The attribute, if present, must be a valid non-empty URL potentially surrounded by spaces. 
      */
    public self_t src(Object val) {
        attr("src", val);
        return (self_t) this;
    }

    /**
      * The srcdoc attribute gives the content of the page that the nested browsing context is to contain. The value of the attribute is the source of an iframe srcdoc document. 
      */
    public self_t srcdoc(Object val) {
        attr("srcdoc", val);
        return (self_t) this;
    }

    /**
      * <!-- NAME -->The name attribute, if present, must be a valid browsing context name. The given value is used to name the nested browsing context. When the browsing context is created, if the attribute is present, the browsing context name must be set to the value of this attribute; otherwise, the browsing context name must be set to the empty string. 
      */
    public self_t name(Object val) {
        attr("name", val);
        return (self_t) this;
    }

    /**
      * <!-- SANDBOX -->The sandbox attribute, when specified, enables a set of extra restrictions on any content hosted by the iframe. Its value must be an unordered set of unique space-separated tokens that are ASCII case-insensitive. The allowed values are allow-forms, allow-pointer-lock, allow-popups, allow-same-origin, allow-scripts, and allow-top-navigation. 
      */
    public self_t sandbox(Object val) {
        attr("sandbox", val);
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
