package net.bodz.bas.html.dom.tag;

import net.bodz.bas.html.dom.AbstractHtmlTag;
import net.bodz.bas.html.dom.IHtmlTag;

/**
  * <!--TOPIC:HTML-->The dfn element represents the defining instance of a term. The paragraph, description list group, or section that is the nearest ancestor of the dfn element must also contain the definition(s) for the term given by the dfn element. 
  */
class _HtmlDfnTag<self_t extends IHtmlTag>
        extends AbstractHtmlTag<self_t> {

    public _HtmlDfnTag(IHtmlTag parent, String tagName) {
        super(parent, tagName);
    }

}
