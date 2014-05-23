package net.bodz.bas.html.dom.tag;

import net.bodz.bas.html.dom.AbstractHtmlTag;
import net.bodz.bas.html.dom.IHtmlTag;

/**
  * <!--TOPIC:HTML-->The cite element represents a reference to a creative work. It must include the title of the work or the name of the author(person, people or organization) or an URL reference, which may be in an abbreviated form as per the conventions used for the addition of citation metadata. 
  */
class _HtmlCiteTag<self_t extends IHtmlTag>
        extends AbstractHtmlTag<self_t> {

    public _HtmlCiteTag(IHtmlTag parent, String tagName) {
        super(parent, tagName);
    }

}
