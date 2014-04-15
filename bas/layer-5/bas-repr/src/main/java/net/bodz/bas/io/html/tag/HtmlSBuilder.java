package net.bodz.bas.io.html.tag;

import net.bodz.bas.io.html.DecoratedHtmlTagBuilder;
import net.bodz.bas.io.xml.IXmlTagBuilder;

/**
  * The s element is not appropriate when indicating document edits; to mark a span of text as having been removed from a document, use the del element. 
  */
public class HtmlSBuilder
        extends DecoratedHtmlTagBuilder<HtmlSBuilder> {

    public HtmlSBuilder(IXmlTagBuilder o) {
        super(o);
    }

}
