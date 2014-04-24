package net.bodz.bas.io.html.tag;

import net.bodz.bas.io.html.DecoratedHtmlTagBuilder;
import net.bodz.bas.io.html.IHtmlTagBuilder;
import net.bodz.bas.io.xml.IXmlTagBuilder;

/**
  * The em element also isn't intended to convey importance; for that purpose, the strong element is more appropriate. 
  */
class _HtmlEmBuilder<self_t extends IHtmlTagBuilder<?>>
        extends DecoratedHtmlTagBuilder<self_t> {

    public _HtmlEmBuilder(IXmlTagBuilder o) {
        super(o);
    }

}
