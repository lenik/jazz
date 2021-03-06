package net.bodz.bas.html.dom.tag.gen;

import net.bodz.bas.html.dom.IHtmlTag;
import net.bodz.bas.html.dom.MutableHtmlTag;

/**
  * <!--TOPIC:HTML-->The style element allows authors to embed style information in their documents. The style element is one of several inputs to the styling processing model. The element does not represent content for the user. 
  */
@SuppressWarnings("unchecked")
public class _MutableStyle<self_t extends IHtmlTag>
        extends MutableHtmlTag<self_t> {

    public _MutableStyle(IHtmlTag parent) {
        super(parent, "style");
    }

    /**
      * The media attribute says which media the styles apply to. The value must be a valid media query. The user agent must apply the styles when the media attribute's value matches the environment and the other relevant conditions apply, and must not apply them otherwise.<!-- note similar text in section --> 
      */
    public self_t media(Object val) {
        attr("media", val);
        return (self_t) this;
    }

    /**
      * The type attribute gives the styling language. If the attribute is present, its value must be a valid MIME type that designates a styling language. The charset parameter must not be specified. The default value for the type attribute, which is used if the attribute is absent, is "text/css". [RFC2318] 
      */
    public self_t type(Object val) {
        attr("type", val);
        return (self_t) this;
    }

}
