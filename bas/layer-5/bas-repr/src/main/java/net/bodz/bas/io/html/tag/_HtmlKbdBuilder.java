package net.bodz.bas.io.html.tag;

import net.bodz.bas.io.html.DecoratedHtmlTagBuilder;
import net.bodz.bas.io.html.IHtmlTagBuilder;
import net.bodz.bas.io.xml.IXmlTagBuilder;

/**
  * <!--TOPIC:HTML-->The kbd element represents user input (typically keyboard input, although it may also be used to represent other input, such as voice commands). 
  */
class _HtmlKbdBuilder<self_t extends IHtmlTagBuilder<?>>
        extends DecoratedHtmlTagBuilder<self_t> {

    public _HtmlKbdBuilder(IXmlTagBuilder o) {
        super(o);
    }

}
