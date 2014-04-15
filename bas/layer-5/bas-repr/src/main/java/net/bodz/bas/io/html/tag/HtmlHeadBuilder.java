package net.bodz.bas.io.html.tag;

import net.bodz.bas.io.html.DecoratedHtmlTagBuilder;
import net.bodz.bas.io.xml.IXmlTagBuilder;

/**
  * The title element is a required child in most situations, but when a higher-level protocol provides title information, e.g. in the Subject line of an e-mail when HTML is used as an e-mail authoring format, the title element can be omitted. 
  */
public class HtmlHeadBuilder
        extends DecoratedHtmlTagBuilder<HtmlHeadBuilder> {

    public HtmlHeadBuilder(IXmlTagBuilder o) {
        super(o);
    }

}
