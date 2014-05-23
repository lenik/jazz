package net.bodz.bas.html.dom.tag;

import net.bodz.bas.html.dom.AbstractHtmlTag;
import net.bodz.bas.html.dom.IHtmlTag;

/**
  * The footer element is not sectioning content; it doesn't introduce a new section. 
  */
class _HtmlFooterTag<self_t extends IHtmlTag>
        extends AbstractHtmlTag<self_t> {

    public _HtmlFooterTag(IHtmlTag parent, String tagName) {
        super(parent, tagName);
    }

}
