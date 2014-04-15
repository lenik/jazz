package net.bodz.bas.io.html.tag;

import net.bodz.bas.io.html.DecoratedHtmlTagBuilder;
import net.bodz.bas.io.xml.IXmlTagBuilder;

/**
  * <!--TOPIC:HTML-->The bdo element represents explicit text directionality formatting control for its children. It allows authors to override the Unicode bidirectional algorithm by explicitly specifying a direction override. [BIDI] 
  */
public class HtmlBdoBuilder
        extends DecoratedHtmlTagBuilder<HtmlBdoBuilder> {

    public HtmlBdoBuilder(IXmlTagBuilder o) {
        super(o);
    }

}
