package net.bodz.bas.html.dom.tag;

import net.bodz.bas.html.dom.AbstractHtmlTag;
import net.bodz.bas.html.dom.IHtmlTag;

/**
  * <!--TOPIC:HTML-->The code element represents a fragment of computer code. This could be an XML element name, a file name, a computer program, or any other string that a computer would recognize. 
  */
class _HtmlCodeTag<self_t extends IHtmlTag>
        extends AbstractHtmlTag<self_t> {

    public _HtmlCodeTag(IHtmlTag parent, String tagName) {
        super(parent, tagName);
    }

}
