package net.bodz.bas.html.dom.tag;

import net.bodz.bas.html.dom.AbstractHtmlTag;
import net.bodz.bas.html.dom.IHtmlTag;

/**
  * <!--TOPIC:HTML-->The article element represents a complete, or self-contained, composition in a document, page, application, or site and that is, in principle, independently distributable or reusable, e.g. in syndication. This could be a forum post, a magazine or newspaper article, a blog entry, a user-submitted comment, an interactive widget or gadget, or any other independent item of content. 
  */
class _HtmlArticleTag<self_t extends IHtmlTag>
        extends AbstractHtmlTag<self_t> {

    public _HtmlArticleTag(IHtmlTag parent, String tagName) {
        super(parent, tagName);
    }

}
