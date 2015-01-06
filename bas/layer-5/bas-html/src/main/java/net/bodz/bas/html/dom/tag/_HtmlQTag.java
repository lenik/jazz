package net.bodz.bas.html.dom.tag;

import net.bodz.bas.html.dom.AbstractHtmlTag;
import net.bodz.bas.html.dom.IHtmlTag;

/**
  * The q element must not be used in place of quotation marks that do not represent quotes; for example, it is inappropriate to use the q element for marking up sarcastic statements. 
  */
@SuppressWarnings("unchecked")
class _HtmlQTag<self_t extends IHtmlTag>
        extends AbstractHtmlTag<self_t> {

    public _HtmlQTag(IHtmlTag parent, String tagName) {
        super(parent, tagName);
    }

    public self_t cite(Object val) {
        attr("cite", val);
        return (self_t) this;
    }

}
