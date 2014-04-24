package net.bodz.bas.io.html.tag;

import net.bodz.bas.io.html.DecoratedHtmlTagBuilder;
import net.bodz.bas.io.html.IHtmlTagBuilder;
import net.bodz.bas.io.xml.IXmlTagBuilder;

/**
  * The datalist element is hooked up to an input element using the list attribute on the input element. 
  */
class _HtmlDatalistBuilder<self_t extends IHtmlTagBuilder<?>>
        extends DecoratedHtmlTagBuilder<self_t> {

    public _HtmlDatalistBuilder(IXmlTagBuilder o) {
        super(o);
    }

}
