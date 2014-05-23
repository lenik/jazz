package net.bodz.bas.html.dom.tag;

import net.bodz.bas.html.dom.AbstractHtmlTag;
import net.bodz.bas.html.dom.IHtmlTag;

/**
  * The sub element can be used inside a var element, for variables that have subscripts. 
  */
class _HtmlSubandsupTag<self_t extends IHtmlTag>
        extends AbstractHtmlTag<self_t> {

    public _HtmlSubandsupTag(IHtmlTag parent, String tagName) {
        super(parent, tagName);
    }

}
