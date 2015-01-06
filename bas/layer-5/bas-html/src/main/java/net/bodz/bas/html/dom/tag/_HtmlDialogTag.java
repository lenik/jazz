package net.bodz.bas.html.dom.tag;

import net.bodz.bas.html.dom.AbstractHtmlTag;
import net.bodz.bas.html.dom.IHtmlTag;

/**
  * <!--TOPIC:HTML-->The dialog element represents a part of an application that a user interacts with to perform a task, for example a dialog box, inspector, or window. 
  */
@SuppressWarnings("unchecked")
class _HtmlDialogTag<self_t extends IHtmlTag>
        extends AbstractHtmlTag<self_t> {

    public _HtmlDialogTag(IHtmlTag parent, String tagName) {
        super(parent, tagName);
    }

    /**
      * The open attribute is a boolean attribute. When specified, it indicates that the dialog element is active and that the user can interact with it. 
      */
    public self_t open(Object val) {
        attr("open", val);
        return (self_t) this;
    }

}
