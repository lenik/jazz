package net.bodz.bas.io.html.tag;

import net.bodz.bas.io.html.DecoratedHtmlTagBuilder;
import net.bodz.bas.io.xml.IXmlTagBuilder;

/**
  * The sub element can be used inside a var element, for variables that have subscripts. 
  */
public class HtmlSubandsupBuilder
        extends DecoratedHtmlTagBuilder<HtmlSubandsupBuilder> {

    public HtmlSubandsupBuilder(IXmlTagBuilder o) {
        super(o);
    }

}
