package net.bodz.bas.io.html.tag;

import net.bodz.bas.io.html.DecoratedHtmlTagBuilder;
import net.bodz.bas.io.xml.IXmlTagBuilder;

/**
  * <!--TOPIC:HTML-->The div element has no special meaning at all. It represents its children. It can be used with the class, lang, and title attributes to mark up semantics common to a group of consecutive elements. 
  */
public class HtmlDivBuilder
        extends DecoratedHtmlTagBuilder<HtmlDivBuilder> {

    public HtmlDivBuilder(IXmlTagBuilder o) {
        super(o);
    }

}
