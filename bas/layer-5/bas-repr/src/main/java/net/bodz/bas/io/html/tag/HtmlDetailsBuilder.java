package net.bodz.bas.io.html.tag;

import net.bodz.bas.io.html.DecoratedHtmlTagBuilder;
import net.bodz.bas.io.xml.IXmlTagBuilder;

/**
  * The details element is not appropriate for footnotes. Please see the section on footnotes for details on how to mark up footnotes. 
  */
public class HtmlDetailsBuilder
        extends DecoratedHtmlTagBuilder<HtmlDetailsBuilder> {

    public HtmlDetailsBuilder(IXmlTagBuilder o) {
        super(o);
    }

    /**
      * The open content attribute is a boolean attribute. If present, it indicates that both the summary and the additional information is to be shown to the user. If the attribute is absent, only the summary is to be shown. 
      */
    public HtmlDetailsBuilder open(String val) {
        attr("open", val);
        return this;
    }

}
