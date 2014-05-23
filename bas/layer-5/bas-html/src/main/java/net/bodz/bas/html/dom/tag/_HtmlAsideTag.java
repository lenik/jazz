package net.bodz.bas.html.dom.tag;

import net.bodz.bas.html.dom.AbstractHtmlTag;
import net.bodz.bas.html.dom.IHtmlTag;

/**
  * <!--TOPIC:HTML-->The aside element represents a section of a page that consists of content that is tangentially related to the content around the aside element, and which could be considered separate from that content. Such sections are often represented as sidebars in printed typography. 
  */
class _HtmlAsideTag<self_t extends IHtmlTag>
        extends AbstractHtmlTag<self_t> {

    public _HtmlAsideTag(IHtmlTag parent, String tagName) {
        super(parent, tagName);
    }

}
