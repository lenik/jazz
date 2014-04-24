package net.bodz.bas.io.html.tag;

import net.bodz.bas.io.html.DecoratedHtmlTagBuilder;
import net.bodz.bas.io.html.IHtmlTagBuilder;
import net.bodz.bas.io.xml.IXmlTagBuilder;

/**
  * The template element allows for the declaration of document fragments which are unused by the document when loaded, but are parsed as HTML and are available at runtime for use by the web page. 
  */
class _HtmlTemplateBuilder<self_t extends IHtmlTagBuilder<?>>
        extends DecoratedHtmlTagBuilder<self_t> {

    public _HtmlTemplateBuilder(IXmlTagBuilder o) {
        super(o);
    }

}
