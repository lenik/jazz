package net.bodz.bas.io.html.tag;

import net.bodz.bas.io.html.DecoratedHtmlTagBuilder;
import net.bodz.bas.io.xml.IXmlTagBuilder;

/**
  * <!--TOPIC:HTML-->The dialog element represents a part of an application that a user interacts with to perform a task, for example a dialog box, inspector, or window. 
  */
public class HtmlDialogBuilder
        extends DecoratedHtmlTagBuilder<HtmlDialogBuilder> {

    public HtmlDialogBuilder(IXmlTagBuilder o) {
        super(o);
    }

    /**
      * The open attribute is a boolean attribute. When specified, it indicates that the dialog element is active and that the user can interact with it. 
      */
    public HtmlDialogBuilder open(String val) {
        attr("open", val);
        return this;
    }

}
