package net.bodz.bas.html.dom.tag.gen;

import net.bodz.bas.html.dom.IHtmlTag;
import net.bodz.bas.html.dom.MutableHtmlTag;

/**
  * The footer element is not sectioning content; it doesn't introduce a new section. 
  */
public class _MutableFooter<self_t extends IHtmlTag>
        extends MutableHtmlTag<self_t> {

    public _MutableFooter(IHtmlTag parent) {
        super(parent, "footer");
    }

}
