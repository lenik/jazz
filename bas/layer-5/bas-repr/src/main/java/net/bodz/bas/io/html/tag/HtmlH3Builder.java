package net.bodz.bas.io.html.tag;

import net.bodz.bas.io.html.DecoratedHtmlTagBuilder;
import net.bodz.bas.io.xml.IXmlTagBuilder;

/**
  * These elements have a rank given by the number in their name. The h1 element is said to have the highest rank, the h6 element has the lowest rank, and two elements with the same name have equal rank. 
  */
public class HtmlH3Builder
        extends DecoratedHtmlTagBuilder<HtmlH3Builder> {

    public HtmlH3Builder(IXmlTagBuilder o) {
        super(o);
    }

}
