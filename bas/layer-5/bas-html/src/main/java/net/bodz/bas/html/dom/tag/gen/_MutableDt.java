package net.bodz.bas.html.dom.tag.gen;

import net.bodz.bas.html.dom.IHtmlTag;
import net.bodz.bas.html.dom.MutableHtmlTag;

/**
  * The dt element itself, when used in a dl element, does not indicate that its contents are a term being defined, but this can be indicated using the dfn element. 
  */
public class _MutableDt<self_t extends IHtmlTag>
        extends MutableHtmlTag<self_t> {

    public _MutableDt(IHtmlTag parent) {
        super(parent, "dt");
    }

}
