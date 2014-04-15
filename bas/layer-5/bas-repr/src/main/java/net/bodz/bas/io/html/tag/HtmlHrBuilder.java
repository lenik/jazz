package net.bodz.bas.io.html.tag;

import net.bodz.bas.io.html.DecoratedHtmlTagBuilder;
import net.bodz.bas.io.xml.IXmlTagBuilder;

/**
  * The hr element does not affect the document's outline. 
  */
public class HtmlHrBuilder
        extends DecoratedHtmlTagBuilder<HtmlHrBuilder> {

    public HtmlHrBuilder(IXmlTagBuilder o) {
        super(o);
    }

}
