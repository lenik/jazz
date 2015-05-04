package net.bodz.bas.html.dom.tag;

import net.bodz.bas.html.dom.AbstractHtmlTag;
import net.bodz.bas.html.dom.IHtmlTag;

/**
  * In this example, the heading is really "Flowers, Bees, and Honey", but the author has added a light-hearted addition to the heading. The strong element is thus used to mark up the first part to distinguish it from the latter part. 
  */
public class _HtmlStrongTag<self_t extends IHtmlTag>
        extends AbstractHtmlTag<self_t> {

    protected _HtmlStrongTag(IHtmlTag parent, String tagName) {
        super(parent, tagName);
    }

}
