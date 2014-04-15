package net.bodz.bas.io.html.tag;

import net.bodz.bas.io.html.DecoratedHtmlTagBuilder;
import net.bodz.bas.io.xml.IXmlTagBuilder;

/**
  * <!--TOPIC:HTML-->The style element allows authors to embed style information in their documents. The style element is one of several inputs to the styling processing model. The element does not represent content for the user. 
  */
public class HtmlStyleBuilder
        extends DecoratedHtmlTagBuilder<HtmlStyleBuilder> {

    public HtmlStyleBuilder(IXmlTagBuilder o) {
        super(o);
    }

    /**
      * The media attribute says which media the styles apply to. The value must be a valid media query. The user agent must apply the styles when the media attribute's value matches the environment and the other relevant conditions apply, and must not apply them otherwise.<!-- note similar text in section --> 
      */
    public HtmlStyleBuilder media(String val) {
        attr("media", val);
        return this;
    }

    /**
      * The type attribute gives the styling language. If the attribute is present, its value must be a valid MIME type that designates a styling language. The charset parameter must not be specified. The default value for the type attribute, which is used if the attribute is absent, is "text/css". [RFC2318] 
      */
    public HtmlStyleBuilder type(String val) {
        attr("type", val);
        return this;
    }

    /**
      * The scoped attribute is a boolean attribute. If present, it indicates that the styles are intended just for the subtree rooted at the style element's parent element, as opposed to the whole Document. 
      */
    public HtmlStyleBuilder scoped(String val) {
        attr("scoped", val);
        return this;
    }

}
