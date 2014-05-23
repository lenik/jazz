package net.bodz.bas.html.dom.tag;

import net.bodz.bas.html.dom.AbstractHtmlTag;
import net.bodz.bas.html.dom.IHtmlTag;

/**
  * The datalist element is hooked up to an input element using the list attribute on the input element. 
  */
class _HtmlDatalistTag<self_t extends IHtmlTag>
        extends AbstractHtmlTag<self_t> {

    public _HtmlDatalistTag(IHtmlTag parent, String tagName) {
        super(parent, tagName);
    }

}
