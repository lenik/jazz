package net.bodz.bas.html.dom.tag.gen;

import net.bodz.bas.html.dom.IHtmlTag;
import net.bodz.bas.html.dom.MutableHtmlTag;

/**
  * The b element should be used as a last resort when no other element is more appropriate. In particular, headings should use the h1 to h6 elements, stress emphasis should use the em element, importance should be denoted with the strong element, and text marked or highlighted should use the mark element. 
  */
public class _MutableB<self_t extends IHtmlTag>
        extends MutableHtmlTag<self_t> {

    public _MutableB(IHtmlTag parent, String tagName) {
        super(parent, tagName);
    }

}
