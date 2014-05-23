package net.bodz.bas.html.dom.tag;

import net.bodz.bas.html.dom.AbstractHtmlTag;
import net.bodz.bas.html.dom.IHtmlTag;

/**
  * The dt element itself, when used in a dl element, does not indicate that its contents are a term being defined, but this can be indicated using the dfn element. 
  */
class _HtmlDtTag<self_t extends IHtmlTag>
        extends AbstractHtmlTag<self_t> {

    public _HtmlDtTag(IHtmlTag parent, String tagName) {
        super(parent, tagName);
    }

}
