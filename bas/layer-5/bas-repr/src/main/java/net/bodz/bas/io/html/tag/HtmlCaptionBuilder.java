package net.bodz.bas.io.html.tag;

import net.bodz.bas.io.html.DecoratedHtmlTagBuilder;
import net.bodz.bas.io.xml.IXmlTagBuilder;

/**
  * The caption element takes part in the table model. 
  */
public class HtmlCaptionBuilder
        extends DecoratedHtmlTagBuilder<HtmlCaptionBuilder> {

    public HtmlCaptionBuilder(IXmlTagBuilder o) {
        super(o);
    }

}
