package net.bodz.bas.io.html.tag;

import net.bodz.bas.io.html.DecoratedHtmlTagBuilder;
import net.bodz.bas.io.xml.IXmlTagBuilder;

/**
  * <!--TOPIC:HTML-->The base element allows authors to specify the document base URL for the purposes of resolving relative URLs, and the name of the default browsing context for the purposes of following hyperlinks. The element does not represent any content beyond this information. 
  */
public class HtmlBaseBuilder
        extends DecoratedHtmlTagBuilder<HtmlBaseBuilder> {

    public HtmlBaseBuilder(IXmlTagBuilder o) {
        super(o);
    }

    /**
      * The href content attribute, if specified, must contain a valid URL potentially surrounded by spaces. 
      */
    public HtmlBaseBuilder href(String val) {
        attr("href", val);
        return this;
    }

    /**
      * The target attribute, if specified, must contain a valid browsing context name or keyword, which specifies which browsing context is to be used as the default when hyperlinks and forms in the Document cause navigation. 
      */
    public HtmlBaseBuilder target(String val) {
        attr("target", val);
        return this;
    }

}
