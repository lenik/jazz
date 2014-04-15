package net.bodz.bas.io.html.tag;

import net.bodz.bas.io.html.DecoratedHtmlTagBuilder;
import net.bodz.bas.io.xml.IXmlTagBuilder;

/**
  * <!--TOPIC:HTML-->The span element doesn't mean anything on its own, but can be useful when used together with the global attributes, e.g. class, lang, or dir. It represents its children. 
  */
public class HtmlSpanBuilder
        extends DecoratedHtmlTagBuilder<HtmlSpanBuilder> {

    public HtmlSpanBuilder(IXmlTagBuilder o) {
        super(o);
    }

}
