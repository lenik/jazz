package net.bodz.bas.html.dom.tag;

import net.bodz.bas.html.dom.AbstractHtmlTag;
import net.bodz.bas.html.dom.IHtmlTag;

/**
  * <!--TOPIC:HTML-->The div element has no special meaning at all. It represents its children. It can be used with the class, lang, and title attributes to mark up semantics common to a group of consecutive elements. 
  */
public class _HtmlDivTag<self_t extends IHtmlTag>
        extends AbstractHtmlTag<self_t> {

    protected _HtmlDivTag(IHtmlTag parent, String tagName) {
        super(parent, tagName);
    }

}
