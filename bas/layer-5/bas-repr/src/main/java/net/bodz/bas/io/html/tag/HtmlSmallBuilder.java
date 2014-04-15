package net.bodz.bas.io.html.tag;

import net.bodz.bas.io.html.DecoratedHtmlTagBuilder;
import net.bodz.bas.io.xml.IXmlTagBuilder;

/**
  * The small element should not be used for extended spans of text, such as multiple paragraphs, lists, or sections of text. It is only intended for short runs of text. The text of a page listing terms of use, for instance, would not be a suitable candidate for the small element: in such a case, the text is not a side comment, it is the main content of the page. 
  */
public class HtmlSmallBuilder
        extends DecoratedHtmlTagBuilder<HtmlSmallBuilder> {

    public HtmlSmallBuilder(IXmlTagBuilder o) {
        super(o);
    }

}
