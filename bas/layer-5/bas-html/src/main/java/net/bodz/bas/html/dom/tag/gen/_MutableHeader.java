package net.bodz.bas.html.dom.tag.gen;

import net.bodz.bas.html.dom.IHtmlTag;
import net.bodz.bas.html.dom.MutableHtmlTag;

/**
  * The header element is not sectioning content; it doesn't introduce a new section. 
  */
public class _MutableHeader<self_t extends IHtmlTag>
        extends MutableHtmlTag<self_t> {

    public _MutableHeader(IHtmlTag parent) {
        super(parent, "header");
    }

}
