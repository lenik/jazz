package net.bodz.bas.io.html.tag;

import net.bodz.bas.io.html.DecoratedHtmlTagBuilder;
import net.bodz.bas.io.html.IHtmlTagBuilder;
import net.bodz.bas.io.xml.IXmlTagBuilder;

/**
  * The q element must not be used in place of quotation marks that do not represent quotes; for example, it is inappropriate to use the q element for marking up sarcastic statements. 
  */
@SuppressWarnings("unchecked")
class _HtmlQBuilder<self_t extends IHtmlTagBuilder<?>>
        extends DecoratedHtmlTagBuilder<self_t> {

    public _HtmlQBuilder(IXmlTagBuilder o) {
        super(o);
    }

    public self_t cite(String val) {
        attr("cite", val);
        return (self_t) this;
    }

}
