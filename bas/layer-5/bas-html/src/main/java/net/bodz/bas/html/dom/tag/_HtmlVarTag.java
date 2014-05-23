package net.bodz.bas.html.dom.tag;

import net.bodz.bas.html.dom.AbstractHtmlTag;
import net.bodz.bas.html.dom.IHtmlTag;

/**
  * <!--TOPIC:HTML-->The var element represents a variable. This could be an actual variable in a mathematical expression or programming context, an identifier representing a constant, a symbol identifying a physical quantity, a function parameter, or just be a term used as a placeholder in prose. 
  */
class _HtmlVarTag<self_t extends IHtmlTag>
        extends AbstractHtmlTag<self_t> {

    public _HtmlVarTag(IHtmlTag parent, String tagName) {
        super(parent, tagName);
    }

}
