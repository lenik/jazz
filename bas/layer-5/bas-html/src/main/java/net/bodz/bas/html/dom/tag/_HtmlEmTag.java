package net.bodz.bas.html.dom.tag;

import net.bodz.bas.html.dom.AbstractHtmlTag;
import net.bodz.bas.html.dom.IHtmlTag;

/**
  * The em element also isn't intended to convey importance; for that purpose, the strong element is more appropriate. 
  */
class _HtmlEmTag<self_t extends IHtmlTag>
        extends AbstractHtmlTag<self_t> {

    public _HtmlEmTag(IHtmlTag parent, String tagName) {
        super(parent, tagName);
    }

}
