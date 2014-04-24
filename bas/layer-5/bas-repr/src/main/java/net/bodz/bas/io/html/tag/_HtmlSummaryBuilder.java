package net.bodz.bas.io.html.tag;

import net.bodz.bas.io.html.DecoratedHtmlTagBuilder;
import net.bodz.bas.io.html.IHtmlTagBuilder;
import net.bodz.bas.io.xml.IXmlTagBuilder;

/**
  * <!--TOPIC:HTML-->The summary element represents a summary, caption, or legend for the rest of the contents of the summary element's parent details element, if any. 
  */
class _HtmlSummaryBuilder<self_t extends IHtmlTagBuilder<?>>
        extends DecoratedHtmlTagBuilder<self_t> {

    public _HtmlSummaryBuilder(IXmlTagBuilder o) {
        super(o);
    }

}
