package net.bodz.bas.html.dom.tag.gen;

import net.bodz.bas.html.dom.IHtmlTag;
import net.bodz.bas.html.dom.MutableHtmlTag;

/**
  * The q element must not be used in place of quotation marks that do not represent quotes; for example, it is inappropriate to use the q element for marking up sarcastic statements. 
  */
@SuppressWarnings("unchecked")
public class _MutableQ<self_t extends IHtmlTag>
        extends MutableHtmlTag<self_t> {

    public _MutableQ(IHtmlTag parent) {
        super(parent, "q");
    }

    public self_t cite(Object val) {
        attr("cite", val);
        return (self_t) this;
    }

}
