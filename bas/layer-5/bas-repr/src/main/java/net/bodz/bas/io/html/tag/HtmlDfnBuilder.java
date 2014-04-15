package net.bodz.bas.io.html.tag;

import net.bodz.bas.io.html.DecoratedHtmlTagBuilder;
import net.bodz.bas.io.xml.IXmlTagBuilder;

/**
  * <!--TOPIC:HTML-->The dfn element represents the defining instance of a term. The paragraph, description list group, or section that is the nearest ancestor of the dfn element must also contain the definition(s) for the term given by the dfn element. 
  */
public class HtmlDfnBuilder
        extends DecoratedHtmlTagBuilder<HtmlDfnBuilder> {

    public HtmlDfnBuilder(IXmlTagBuilder o) {
        super(o);
    }

}
