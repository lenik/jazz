package net.bodz.bas.html.dom.tag.gen;

import net.bodz.bas.html.dom.IHtmlTag;
import net.bodz.bas.html.dom.MutableHtmlTag;

/**
  * <!--TOPIC:HTML-->The del element represents a removal from the document. 
  */
@SuppressWarnings("unchecked")
public class _MutableDel<self_t extends IHtmlTag>
        extends MutableHtmlTag<self_t> {

    public _MutableDel(IHtmlTag parent) {
        super(parent, "del");
    }

    public self_t cite(Object val) {
        attr("cite", val);
        return (self_t) this;
    }

    public self_t datetime(Object val) {
        attr("datetime", val);
        return (self_t) this;
    }

}
