package net.bodz.bas.io.html.tag;

import net.bodz.bas.io.html.DecoratedHtmlTagBuilder;
import net.bodz.bas.io.html.IHtmlTagBuilder;
import net.bodz.bas.io.xml.IXmlTagBuilder;

/**
  * The s element is not appropriate when indicating document edits; to mark a span of text as having been removed from a document, use the del element. 
  */
class _HtmlSBuilder<self_t extends IHtmlTagBuilder<?>>
        extends DecoratedHtmlTagBuilder<self_t> {

    public _HtmlSBuilder(IXmlTagBuilder o) {
        super(o);
    }

}
