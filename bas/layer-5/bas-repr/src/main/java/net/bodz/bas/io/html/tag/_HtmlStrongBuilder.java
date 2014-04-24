package net.bodz.bas.io.html.tag;

import net.bodz.bas.io.html.DecoratedHtmlTagBuilder;
import net.bodz.bas.io.html.IHtmlTagBuilder;
import net.bodz.bas.io.xml.IXmlTagBuilder;

/**
  * In this example, the heading is really "Flowers, Bees, and Honey", but the author has added a light-hearted addition to the heading. The strong element is thus used to mark up the first part to distinguish it from the latter part. 
  */
class _HtmlStrongBuilder<self_t extends IHtmlTagBuilder<?>>
        extends DecoratedHtmlTagBuilder<self_t> {

    public _HtmlStrongBuilder(IXmlTagBuilder o) {
        super(o);
    }

}
