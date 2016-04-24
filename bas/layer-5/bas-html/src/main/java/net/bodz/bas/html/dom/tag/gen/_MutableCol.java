package net.bodz.bas.html.dom.tag.gen;

import net.bodz.bas.html.dom.IHtmlTag;
import net.bodz.bas.html.dom.MutableHtmlTag;

/**
  * The col element and its span attribute take part in the table model. 
  */
@SuppressWarnings("unchecked")
public class _MutableCol<self_t extends IHtmlTag>
        extends MutableHtmlTag<self_t> {

    public _MutableCol(IHtmlTag parent) {
        super(parent, "col");
    }

    public self_t span(Object val) {
        attr("span", val);
        return (self_t) this;
    }

}
