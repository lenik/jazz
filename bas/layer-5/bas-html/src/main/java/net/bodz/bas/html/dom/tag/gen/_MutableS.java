package net.bodz.bas.html.dom.tag.gen;

import net.bodz.bas.html.dom.IHtmlTag;
import net.bodz.bas.html.dom.MutableHtmlTag;

/**
  * The s element is not appropriate when indicating document edits; to mark a span of text as having been removed from a document, use the del element. 
  */
public class _MutableS<self_t extends IHtmlTag>
        extends MutableHtmlTag<self_t> {

    public _MutableS(IHtmlTag parent, String tagName) {
        super(parent, tagName);
    }

}
