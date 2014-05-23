package net.bodz.bas.html.dom.tag;

import net.bodz.bas.html.dom.AbstractHtmlTag;
import net.bodz.bas.html.dom.IHtmlTag;

/**
  * <!--TOPIC:HTML-->The u element represents a span of text with an unarticulated, though explicitly rendered, non-textual annotation, such as labeling the text as being a proper name in Chinese text (a Chinese proper name mark), or labeling the text as being misspelt. 
  */
class _HtmlUTag<self_t extends IHtmlTag>
        extends AbstractHtmlTag<self_t> {

    public _HtmlUTag(IHtmlTag parent, String tagName) {
        super(parent, tagName);
    }

}
