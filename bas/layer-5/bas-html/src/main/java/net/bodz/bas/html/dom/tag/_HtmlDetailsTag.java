package net.bodz.bas.html.dom.tag;

import net.bodz.bas.html.dom.AbstractHtmlTag;
import net.bodz.bas.html.dom.IHtmlTag;

/**
  * The details element is not appropriate for footnotes. Please see the section on footnotes for details on how to mark up footnotes. 
  */
@SuppressWarnings("unchecked")
class _HtmlDetailsTag<self_t extends IHtmlTag>
        extends AbstractHtmlTag<self_t> {

    public _HtmlDetailsTag(IHtmlTag parent, String tagName) {
        super(parent, tagName);
    }

    /**
      * The open content attribute is a boolean attribute. If present, it indicates that both the summary and the additional information is to be shown to the user. If the attribute is absent, only the summary is to be shown. 
      */
    public self_t open(String val) {
        attr("open", val);
        return (self_t) this;
    }

}
