package net.bodz.bas.html.dom.tag;

import net.bodz.bas.html.dom.AbstractHtmlTag;
import net.bodz.bas.html.dom.IHtmlTag;

/**
  * The title element is a required child in most situations, but when a higher-level protocol provides title information, e.g. in the Subject line of an e-mail when HTML is used as an e-mail authoring format, the title element can be omitted. 
  */
class _HtmlHeadTag<self_t extends IHtmlTag>
        extends AbstractHtmlTag<self_t> {

    public _HtmlHeadTag(IHtmlTag parent, String tagName) {
        super(parent, tagName);
    }

}
