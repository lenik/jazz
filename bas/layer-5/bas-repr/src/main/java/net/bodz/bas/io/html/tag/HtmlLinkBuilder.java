package net.bodz.bas.io.html.tag;

import net.bodz.bas.io.html.DecoratedHtmlTagBuilder;
import net.bodz.bas.io.xml.IXmlTagBuilder;

/**
  * <!--TOPIC:HTML-->The link element allows authors to link their document to other resources. 
  */
public class HtmlLinkBuilder
        extends DecoratedHtmlTagBuilder<HtmlLinkBuilder> {

    public HtmlLinkBuilder(IXmlTagBuilder o) {
        super(o);
    }

    public HtmlLinkBuilder href(String val) {
        attr("href", val);
        return this;
    }

    /**
      * The crossorigin attribute is a CORS settings attribute. It is intended for use with external resource links. 
      */
    public HtmlLinkBuilder crossorigin(String val) {
        attr("crossorigin", val);
        return this;
    }

    public HtmlLinkBuilder rel(String val) {
        attr("rel", val);
        return this;
    }

    /**
      * The media attribute says which media the resource applies to. The value must be a valid media query. 
      */
    public HtmlLinkBuilder media(String val) {
        attr("media", val);
        return this;
    }

    /**
      * The hreflang attribute on the link element has the same semantics as the hreflang attribute on a and area elements. 
      */
    public HtmlLinkBuilder hreflang(String val) {
        attr("hreflang", val);
        return this;
    }

    /**
      * The type attribute gives the MIME type of the linked resource. It is purely advisory. The value must be a valid MIME type. 
      */
    public HtmlLinkBuilder type(String val) {
        attr("type", val);
        return this;
    }

    /**
      * The sizes attribute is used with the icon link type. The attribute must not be specified on link elements that do not have a rel attribute that specifies the icon keyword. 
      */
    public HtmlLinkBuilder sizes(String val) {
        attr("sizes", val);
        return this;
    }

}
