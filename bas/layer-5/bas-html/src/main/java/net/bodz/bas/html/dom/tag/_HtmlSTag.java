package net.bodz.bas.html.dom.tag;

import net.bodz.bas.html.dom.AbstractHtmlTag;
import net.bodz.bas.html.dom.IHtmlTag;

/**
  * The s element is not appropriate when indicating document edits; to mark a span of text as having been removed from a document, use the del element. 
  */
public class _HtmlSTag<self_t extends IHtmlTag>
        extends AbstractHtmlTag<self_t> {

    protected _HtmlSTag(IHtmlTag parent, String tagName) {
        super(parent, tagName);
    }

}
