package net.bodz.bas.io.html.tag;

import net.bodz.bas.io.html.DecoratedHtmlTagBuilder;
import net.bodz.bas.io.xml.IXmlTagBuilder;

/**
  * The p element should not be used when a more specific element is more appropriate. 
  */
public class HtmlPBuilder
        extends DecoratedHtmlTagBuilder<HtmlPBuilder> {

    public HtmlPBuilder(IXmlTagBuilder o) {
        super(o);
    }

}
