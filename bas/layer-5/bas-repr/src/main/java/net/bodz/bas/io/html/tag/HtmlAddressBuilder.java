package net.bodz.bas.io.html.tag;

import net.bodz.bas.io.html.DecoratedHtmlTagBuilder;
import net.bodz.bas.io.xml.IXmlTagBuilder;

/**
  * The address element must not contain information other than contact information. 
  */
public class HtmlAddressBuilder
        extends DecoratedHtmlTagBuilder<HtmlAddressBuilder> {

    public HtmlAddressBuilder(IXmlTagBuilder o) {
        super(o);
    }

}
