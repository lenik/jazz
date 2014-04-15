package net.bodz.bas.io.html.tag;

import net.bodz.bas.io.html.DecoratedHtmlTagBuilder;
import net.bodz.bas.io.xml.IXmlTagBuilder;

/**
  * The dl element is inappropriate for marking up dialogue. Examples of how to mark up dialogue are shown below. 
  */
public class HtmlDlBuilder
        extends DecoratedHtmlTagBuilder<HtmlDlBuilder> {

    public HtmlDlBuilder(IXmlTagBuilder o) {
        super(o);
    }

}
