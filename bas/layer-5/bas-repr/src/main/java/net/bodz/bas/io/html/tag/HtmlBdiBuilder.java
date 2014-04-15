package net.bodz.bas.io.html.tag;

import net.bodz.bas.io.html.DecoratedHtmlTagBuilder;
import net.bodz.bas.io.xml.IXmlTagBuilder;

/**
  * <!--TOPIC:HTML-->The bdi element represents a span of text that is to be isolated from its surroundings for the purposes of bidirectional text formatting. [BIDI] 
  */
public class HtmlBdiBuilder
        extends DecoratedHtmlTagBuilder<HtmlBdiBuilder> {

    public HtmlBdiBuilder(IXmlTagBuilder o) {
        super(o);
    }

}
