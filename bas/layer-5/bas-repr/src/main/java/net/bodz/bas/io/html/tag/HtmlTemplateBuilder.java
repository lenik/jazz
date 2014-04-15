package net.bodz.bas.io.html.tag;

import net.bodz.bas.io.html.DecoratedHtmlTagBuilder;
import net.bodz.bas.io.xml.IXmlTagBuilder;

/**
  * The template element allows for the declaration of document fragments which are unused by the document when loaded, but are parsed as HTML and are available at runtime for use by the web page. 
  */
public class HtmlTemplateBuilder
        extends DecoratedHtmlTagBuilder<HtmlTemplateBuilder> {

    public HtmlTemplateBuilder(IXmlTagBuilder o) {
        super(o);
    }

}
