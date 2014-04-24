package net.bodz.bas.io.html.tag;

import net.bodz.bas.io.html.DecoratedHtmlTagBuilder;
import net.bodz.bas.io.html.IHtmlTagBuilder;
import net.bodz.bas.io.xml.IXmlTagBuilder;

/**
  * The dl element is inappropriate for marking up dialogue. Examples of how to mark up dialogue are shown below. 
  */
class _HtmlDlBuilder<self_t extends IHtmlTagBuilder<?>>
        extends DecoratedHtmlTagBuilder<self_t> {

    public _HtmlDlBuilder(IXmlTagBuilder o) {
        super(o);
    }

}
