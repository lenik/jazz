package net.bodz.bas.io.html.tag;

import net.bodz.bas.io.html.DecoratedHtmlTagBuilder;
import net.bodz.bas.io.html.IHtmlTagBuilder;
import net.bodz.bas.io.xml.IXmlTagBuilder;

/**
  * <!--TOPIC:HTML-->The dialog element represents a part of an application that a user interacts with to perform a task, for example a dialog box, inspector, or window. 
  */
@SuppressWarnings("unchecked")
class _HtmlDialogBuilder<self_t extends IHtmlTagBuilder<?>>
        extends DecoratedHtmlTagBuilder<self_t> {

    public _HtmlDialogBuilder(IXmlTagBuilder o) {
        super(o);
    }

    /**
      * The open attribute is a boolean attribute. When specified, it indicates that the dialog element is active and that the user can interact with it. 
      */
    public self_t open(String val) {
        attr("open", val);
        return (self_t) this;
    }

}
