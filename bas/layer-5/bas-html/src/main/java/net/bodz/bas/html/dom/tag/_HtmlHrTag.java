package net.bodz.bas.html.dom.tag;

import net.bodz.bas.html.dom.AbstractHtmlTag;
import net.bodz.bas.html.dom.IHtmlTag;

/**
  * The hr element does not affect the document's outline. 
  */
class _HtmlHrTag<self_t extends IHtmlTag>
        extends AbstractHtmlTag<self_t> {

    public _HtmlHrTag(IHtmlTag parent, String tagName) {
        super(parent, tagName);
    }

}