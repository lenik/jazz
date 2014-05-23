package net.bodz.bas.html.dom.tag;

import net.bodz.bas.html.dom.AbstractHtmlTag;
import net.bodz.bas.html.dom.IHtmlTag;

/**
  * <!--TOPIC:HTML-->The abbr element represents an abbreviation or acronym, optionally with its expansion. The title attribute may be used to provide an expansion of the abbreviation. The attribute, if specified, must contain an expansion of the abbreviation, and nothing else. 
  */
class _HtmlAbbrTag<self_t extends IHtmlTag>
        extends AbstractHtmlTag<self_t> {

    public _HtmlAbbrTag(IHtmlTag parent, String tagName) {
        super(parent, tagName);
    }

}
