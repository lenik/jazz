package net.bodz.bas.html.dom.tag.gen;

import net.bodz.bas.html.dom.IHtmlTag;
import net.bodz.bas.html.dom.MutableHtmlTag;

/**
  * In this example, the heading is really "Flowers, Bees, and Honey", but the author has added a light-hearted addition to the heading. The strong element is thus used to mark up the first part to distinguish it from the latter part. 
  */
public class _MutableStrong<self_t extends IHtmlTag>
        extends MutableHtmlTag<self_t> {

    public _MutableStrong(IHtmlTag parent) {
        super(parent, "strong");
    }

}
