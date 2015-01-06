package net.bodz.bas.html.dom.tag;

import net.bodz.bas.html.dom.AbstractHtmlTag;
import net.bodz.bas.html.dom.IHtmlTag;

/**
  * <!--TOPIC:HTML-->The ins element represents an addition to the document. 
  */
@SuppressWarnings("unchecked")
class _HtmlInsTag<self_t extends IHtmlTag>
        extends AbstractHtmlTag<self_t> {

    public _HtmlInsTag(IHtmlTag parent, String tagName) {
        super(parent, tagName);
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
