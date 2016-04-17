package net.bodz.bas.html.dom.tag.gen;

import net.bodz.bas.html.dom.IHtmlTag;
import net.bodz.bas.html.dom.MutableHtmlTag;

/**
  * The colgroup element and its span attribute take part in the table model. 
  */
@SuppressWarnings("unchecked")
public class _MutableColgroup<self_t extends IHtmlTag>
        extends MutableHtmlTag<self_t> {

    public _MutableColgroup(IHtmlTag parent, String tagName) {
        super(parent, tagName);
    }

    public self_t span(Object val) {
        attr("span", val);
        return (self_t) this;
    }

}
