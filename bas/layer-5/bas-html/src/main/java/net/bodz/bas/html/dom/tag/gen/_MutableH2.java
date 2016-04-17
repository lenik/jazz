package net.bodz.bas.html.dom.tag.gen;

import net.bodz.bas.html.dom.IHtmlTag;
import net.bodz.bas.html.dom.MutableHtmlTag;

/**
  * These elements have a rank given by the number in their name. The h1 element is said to have the highest rank, the h6 element has the lowest rank, and two elements with the same name have equal rank. 
  */
public class _MutableH2<self_t extends IHtmlTag>
        extends MutableHtmlTag<self_t> {

    public _MutableH2(IHtmlTag parent, String tagName) {
        super(parent, tagName);
    }

}
