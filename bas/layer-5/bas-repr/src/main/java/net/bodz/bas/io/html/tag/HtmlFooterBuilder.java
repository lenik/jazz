package net.bodz.bas.io.html.tag;

import net.bodz.bas.io.html.DecoratedHtmlTagBuilder;
import net.bodz.bas.io.xml.IXmlTagBuilder;

/**
  * The footer element is not sectioning content; it doesn't introduce a new section. 
  */
public class HtmlFooterBuilder
        extends DecoratedHtmlTagBuilder<HtmlFooterBuilder> {

    public HtmlFooterBuilder(IXmlTagBuilder o) {
        super(o);
    }

}
