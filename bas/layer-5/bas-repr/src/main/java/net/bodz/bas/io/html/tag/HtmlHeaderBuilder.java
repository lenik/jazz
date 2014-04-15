package net.bodz.bas.io.html.tag;

import net.bodz.bas.io.html.DecoratedHtmlTagBuilder;
import net.bodz.bas.io.xml.IXmlTagBuilder;

/**
  * The header element is not sectioning content; it doesn't introduce a new section. 
  */
public class HtmlHeaderBuilder
        extends DecoratedHtmlTagBuilder<HtmlHeaderBuilder> {

    public HtmlHeaderBuilder(IXmlTagBuilder o) {
        super(o);
    }

}
