package net.bodz.bas.io.html.tag;

import net.bodz.bas.io.html.DecoratedHtmlTagBuilder;
import net.bodz.bas.io.xml.IXmlTagBuilder;

/**
  * The tr element takes part in the table model. 
  */
public class HtmlTrBuilder
        extends DecoratedHtmlTagBuilder<HtmlTrBuilder> {

    public HtmlTrBuilder(IXmlTagBuilder o) {
        super(o);
    }

}
