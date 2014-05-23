package net.bodz.bas.html.dom.tag;

import net.bodz.bas.html.dom.AbstractHtmlTag;
import net.bodz.bas.html.dom.IHtmlTag;

/**
  * The section element is not a generic container element. When an element is needed only for styling purposes or as a convenience for scripting, authors are encouraged to use the div element instead. A general rule is that the section element is appropriate only if the element's contents would be listed explicitly in the document's outline. 
  */
class _HtmlSectionTag<self_t extends IHtmlTag>
        extends AbstractHtmlTag<self_t> {

    public _HtmlSectionTag(IHtmlTag parent, String tagName) {
        super(parent, tagName);
    }

}
