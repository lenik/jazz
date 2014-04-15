package net.bodz.bas.io.html.tag;

import net.bodz.bas.io.html.DecoratedHtmlTagBuilder;
import net.bodz.bas.io.xml.IXmlTagBuilder;

/**
  * The main element is not suitable for use to identify the main content areas of sub sections of a document or application. The simplest solution is to not mark up the main content of a sub section at all, and just leave it as implicit, but an author could use a grouping content or sectioning content element as appropriate. 
  */
public class HtmlMainBuilder
        extends DecoratedHtmlTagBuilder<HtmlMainBuilder> {

    public HtmlMainBuilder(IXmlTagBuilder o) {
        super(o);
    }

}
