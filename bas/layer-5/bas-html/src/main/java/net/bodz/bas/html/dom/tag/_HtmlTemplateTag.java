package net.bodz.bas.html.dom.tag;

import net.bodz.bas.html.dom.AbstractHtmlTag;
import net.bodz.bas.html.dom.IHtmlTag;

/**
  * The template element allows for the declaration of document fragments which are unused by the document when loaded, but are parsed as HTML and are available at runtime for use by the web page. 
  */
class _HtmlTemplateTag<self_t extends IHtmlTag>
        extends AbstractHtmlTag<self_t> {

    public _HtmlTemplateTag(IHtmlTag parent, String tagName) {
        super(parent, tagName);
    }

}
