package net.bodz.bas.io.html.tag;

import net.bodz.bas.io.html.DecoratedHtmlTagBuilder;
import net.bodz.bas.io.xml.IXmlTagBuilder;

/**
  * The iframe element must be empty in XML documents. 
  */
public class HtmlIframeBuilder
        extends DecoratedHtmlTagBuilder<HtmlIframeBuilder> {

    public HtmlIframeBuilder(IXmlTagBuilder o) {
        super(o);
    }

    /**
      * The src attribute gives the address of a page that the nested browsing context is to contain. The attribute, if present, must be a valid non-empty URL potentially surrounded by spaces. 
      */
    public HtmlIframeBuilder src(String val) {
        attr("src", val);
        return this;
    }

    /**
      * The srcdoc attribute gives the content of the page that the nested browsing context is to contain. The value of the attribute is the source of an iframe srcdoc document. 
      */
    public HtmlIframeBuilder srcdoc(String val) {
        attr("srcdoc", val);
        return this;
    }

    /**
      * <!-- NAME -->The name attribute, if present, must be a valid browsing context name. The given value is used to name the nested browsing context. When the browsing context is created, if the attribute is present, the browsing context name must be set to the value of this attribute; otherwise, the browsing context name must be set to the empty string. 
      */
    public HtmlIframeBuilder name(String val) {
        attr("name", val);
        return this;
    }

    /**
      * <!-- SANDBOX -->The sandbox attribute, when specified, enables a set of extra restrictions on any content hosted by the iframe. Its value must be an unordered set of unique space-separated tokens that are ASCII case-insensitive. The allowed values are allow-forms, allow-pointer-lock, allow-popups, allow-same-origin, allow-scripts, and allow-top-navigation. 
      */
    public HtmlIframeBuilder sandbox(String val) {
        attr("sandbox", val);
        return this;
    }

    /**
      * <!-- SEAMLESS -->The seamless attribute is a boolean attribute. When specified, it indicates that the iframe element's browsing context is to be rendered in a manner that makes it appear to be part of the containing document (seamlessly included in the parent document). 
      */
    public HtmlIframeBuilder seamless(String val) {
        attr("seamless", val);
        return this;
    }

    public HtmlIframeBuilder width(String val) {
        attr("width", val);
        return this;
    }

    public HtmlIframeBuilder height(String val) {
        attr("height", val);
        return this;
    }

}
