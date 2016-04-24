package net.bodz.bas.html.dom.tag.gen;

import net.bodz.bas.html.dom.IHtmlTag;
import net.bodz.bas.html.dom.MutableHtmlTag;

/**
  * The em element also isn't intended to convey importance; for that purpose, the strong element is more appropriate. 
  */
public class _MutableEm<self_t extends IHtmlTag>
        extends MutableHtmlTag<self_t> {

    public _MutableEm(IHtmlTag parent) {
        super(parent, "em");
    }

}
