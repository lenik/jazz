package net.bodz.bas.html.dom.tag;

import net.bodz.bas.html.dom.AbstractHtmlTag;
import net.bodz.bas.html.dom.IHtmlTag;

/**
  * The tbody element takes part in the table model. 
  */
class _HtmlTbodyTag<self_t extends IHtmlTag>
        extends AbstractHtmlTag<self_t> {

    public _HtmlTbodyTag(IHtmlTag parent, String tagName) {
        super(parent, tagName);
    }

}
