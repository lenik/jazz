package net.bodz.bas.io.html.tag;

import net.bodz.bas.io.html.DecoratedHtmlTagBuilder;
import net.bodz.bas.io.xml.IXmlTagBuilder;

/**
  * The tbody element takes part in the table model. 
  */
public class HtmlTbodyBuilder
        extends DecoratedHtmlTagBuilder<HtmlTbodyBuilder> {

    public HtmlTbodyBuilder(IXmlTagBuilder o) {
        super(o);
    }

}
