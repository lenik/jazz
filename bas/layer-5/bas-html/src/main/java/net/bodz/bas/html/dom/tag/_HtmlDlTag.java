package net.bodz.bas.html.dom.tag;

import net.bodz.bas.html.dom.AbstractHtmlTag;
import net.bodz.bas.html.dom.IHtmlTag;

/**
  * The dl element is inappropriate for marking up dialogue. Examples of how to mark up dialogue are shown below. 
  */
class _HtmlDlTag<self_t extends IHtmlTag>
        extends AbstractHtmlTag<self_t> {

    public _HtmlDlTag(IHtmlTag parent, String tagName) {
        super(parent, tagName);
    }

}
