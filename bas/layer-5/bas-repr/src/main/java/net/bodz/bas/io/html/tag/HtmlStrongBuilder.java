package net.bodz.bas.io.html.tag;

import net.bodz.bas.io.html.DecoratedHtmlTagBuilder;
import net.bodz.bas.io.xml.IXmlTagBuilder;

/**
  * In this example, the heading is really "Flowers, Bees, and Honey", but the author has added a light-hearted addition to the heading. The strong element is thus used to mark up the first part to distinguish it from the latter part. 
  */
public class HtmlStrongBuilder
        extends DecoratedHtmlTagBuilder<HtmlStrongBuilder> {

    public HtmlStrongBuilder(IXmlTagBuilder o) {
        super(o);
    }

}
