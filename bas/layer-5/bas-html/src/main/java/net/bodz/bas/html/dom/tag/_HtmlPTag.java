package net.bodz.bas.html.dom.tag;

import net.bodz.bas.html.dom.AbstractHtmlTag;
import net.bodz.bas.html.dom.IHtmlTag;

/**
  * The p element should not be used when a more specific element is more appropriate. 
  */
class _HtmlPTag<self_t extends IHtmlTag>
        extends AbstractHtmlTag<self_t> {

    public _HtmlPTag(IHtmlTag parent, String tagName) {
        super(parent, tagName);
    }

}
