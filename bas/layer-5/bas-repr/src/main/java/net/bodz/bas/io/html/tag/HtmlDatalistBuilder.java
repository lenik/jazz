package net.bodz.bas.io.html.tag;

import net.bodz.bas.io.html.DecoratedHtmlTagBuilder;
import net.bodz.bas.io.xml.IXmlTagBuilder;

/**
  * The datalist element is hooked up to an input element using the list attribute on the input element. 
  */
public class HtmlDatalistBuilder
        extends DecoratedHtmlTagBuilder<HtmlDatalistBuilder> {

    public HtmlDatalistBuilder(IXmlTagBuilder o) {
        super(o);
    }

}
