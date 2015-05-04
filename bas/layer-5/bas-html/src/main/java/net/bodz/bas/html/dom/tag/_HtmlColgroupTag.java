package net.bodz.bas.html.dom.tag;

import net.bodz.bas.html.dom.AbstractHtmlTag;
import net.bodz.bas.html.dom.IHtmlTag;

/**
  * The colgroup element and its span attribute take part in the table model. 
  */
@SuppressWarnings("unchecked")
public class _HtmlColgroupTag<self_t extends IHtmlTag>
        extends AbstractHtmlTag<self_t> {

    protected _HtmlColgroupTag(IHtmlTag parent, String tagName) {
        super(parent, tagName);
    }

    public self_t span(Object val) {
        attr("span", val);
        return (self_t) this;
    }

}
