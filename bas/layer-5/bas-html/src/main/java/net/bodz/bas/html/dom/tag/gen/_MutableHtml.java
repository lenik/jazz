package net.bodz.bas.html.dom.tag.gen;

import net.bodz.bas.html.dom.IHtmlTag;
import net.bodz.bas.html.dom.MutableHtmlTag;

/**
  * The html element in the following example declares that the document's language is English. 
  */
@SuppressWarnings("unchecked")
public class _MutableHtml<self_t extends IHtmlTag>
        extends MutableHtmlTag<self_t> {

    public _MutableHtml(IHtmlTag parent, String tagName) {
        super(parent, tagName);
    }

    /**
      * The manifest attribute only has an effect during the early stages of document load. Changing the attribute dynamically thus has no effect (and thus, no DOM API is provided for this attribute). 
      */
    public self_t manifest(Object val) {
        attr("manifest", val);
        return (self_t) this;
    }

}
