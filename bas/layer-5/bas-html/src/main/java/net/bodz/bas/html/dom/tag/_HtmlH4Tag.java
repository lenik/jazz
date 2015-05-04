package net.bodz.bas.html.dom.tag;

import net.bodz.bas.html.dom.AbstractHtmlTag;
import net.bodz.bas.html.dom.IHtmlTag;

/**
  * These elements have a rank given by the number in their name. The h1 element is said to have the highest rank, the h6 element has the lowest rank, and two elements with the same name have equal rank. 
  */
public class _HtmlH4Tag<self_t extends IHtmlTag>
        extends AbstractHtmlTag<self_t> {

    protected _HtmlH4Tag(IHtmlTag parent, String tagName) {
        super(parent, tagName);
    }

}
