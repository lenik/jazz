package net.bodz.bas.html.dom.tag;

import net.bodz.bas.html.dom.AbstractHtmlTag;
import net.bodz.bas.html.dom.IHtmlTag;

/**
  * The address element must not contain information other than contact information. 
  */
class _HtmlAddressTag<self_t extends IHtmlTag>
        extends AbstractHtmlTag<self_t> {

    public _HtmlAddressTag(IHtmlTag parent, String tagName) {
        super(parent, tagName);
    }

}
