package net.bodz.bas.io.html.tag;

import net.bodz.bas.io.html.DecoratedHtmlTagBuilder;
import net.bodz.bas.io.xml.IXmlTagBuilder;

/**
  * The em element also isn't intended to convey importance; for that purpose, the strong element is more appropriate. 
  */
public class HtmlEmBuilder
        extends DecoratedHtmlTagBuilder<HtmlEmBuilder> {

    public HtmlEmBuilder(IXmlTagBuilder o) {
        super(o);
    }

}
