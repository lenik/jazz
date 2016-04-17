package net.bodz.bas.html.dom.tag.gen;

import net.bodz.bas.html.dom.IHtmlTag;
import net.bodz.bas.html.dom.MutableHtmlTag;

/**
  * The small element should not be used for extended spans of text, such as multiple paragraphs, lists, or sections of text. It is only intended for short runs of text. The text of a page listing terms of use, for instance, would not be a suitable candidate for the small element: in such a case, the text is not a side comment, it is the main content of the page. 
  */
public class _MutableSmall<self_t extends IHtmlTag>
        extends MutableHtmlTag<self_t> {

    public _MutableSmall(IHtmlTag parent, String tagName) {
        super(parent, tagName);
    }

}
