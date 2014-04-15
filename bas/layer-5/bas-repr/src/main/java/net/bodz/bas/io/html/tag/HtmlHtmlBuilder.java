package net.bodz.bas.io.html.tag;

import net.bodz.bas.io.html.DecoratedHtmlTagBuilder;
import net.bodz.bas.io.xml.IXmlTagBuilder;

/**
  * The html element in the following example declares that the document's language is English. 
  */
public class HtmlHtmlBuilder
        extends DecoratedHtmlTagBuilder<HtmlHtmlBuilder> {

    public HtmlHtmlBuilder(IXmlTagBuilder o) {
        super(o);
    }

    /**
      * The manifest attribute only has an effect during the early stages of document load. Changing the attribute dynamically thus has no effect (and thus, no DOM API is provided for this attribute). 
      */
    public HtmlHtmlBuilder manifest(String val) {
        attr("manifest", val);
        return this;
    }

}
