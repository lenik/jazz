package net.bodz.bas.html.dom.tag.gen;

import net.bodz.bas.html.dom.IHtmlTag;
import net.bodz.bas.html.dom.MutableHtmlTag;

/**
  * The section element is not a generic container element. When an element is needed only for styling purposes or as a convenience for scripting, authors are encouraged to use the div element instead. A general rule is that the section element is appropriate only if the element's contents would be listed explicitly in the document's outline. 
  */
public class _MutableSection<self_t extends IHtmlTag>
        extends MutableHtmlTag<self_t> {

    public _MutableSection(IHtmlTag parent, String tagName) {
        super(parent, tagName);
    }

}
