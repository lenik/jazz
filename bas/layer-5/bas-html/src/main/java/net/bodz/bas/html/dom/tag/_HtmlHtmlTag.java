package net.bodz.bas.html.dom.tag;

import net.bodz.bas.html.dom.AbstractHtmlTag;
import net.bodz.bas.html.dom.IHtmlTag;

/**
  * The html element in the following example declares that the document's language is English. 
  */
@SuppressWarnings("unchecked")
class _HtmlHtmlTag<self_t extends IHtmlTag>
        extends AbstractHtmlTag<self_t> {

    public _HtmlHtmlTag(IHtmlTag parent, String tagName) {
        super(parent, tagName);
    }

    /**
      * The manifest attribute only has an effect during the early stages of document load. Changing the attribute dynamically thus has no effect (and thus, no DOM API is provided for this attribute). 
      */
    public self_t manifest(String val) {
        attr("manifest", val);
        return (self_t) this;
    }

}
