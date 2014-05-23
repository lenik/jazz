package net.bodz.bas.html.dom.tag;

import net.bodz.bas.html.dom.AbstractHtmlTag;
import net.bodz.bas.html.dom.IHtmlTag;

/**
  * The tr element takes part in the table model. 
  */
class _HtmlTrTag<self_t extends IHtmlTag>
        extends AbstractHtmlTag<self_t> {

    public _HtmlTrTag(IHtmlTag parent, String tagName) {
        super(parent, tagName);
    }

}
