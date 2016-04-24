package net.bodz.bas.html.dom.tag.gen;

import net.bodz.bas.html.dom.IHtmlTag;
import net.bodz.bas.html.dom.MutableHtmlTag;

/**
  * The datalist element is hooked up to an input element using the list attribute on the input element. 
  */
public class _MutableDatalist<self_t extends IHtmlTag>
        extends MutableHtmlTag<self_t> {

    public _MutableDatalist(IHtmlTag parent) {
        super(parent, "datalist");
    }

}
