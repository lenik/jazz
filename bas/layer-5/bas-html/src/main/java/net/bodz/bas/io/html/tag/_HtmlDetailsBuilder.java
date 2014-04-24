package net.bodz.bas.io.html.tag;

import net.bodz.bas.io.html.DecoratedHtmlTagBuilder;
import net.bodz.bas.io.html.IHtmlTagBuilder;
import net.bodz.bas.io.xml.IXmlTagBuilder;

/**
  * The details element is not appropriate for footnotes. Please see the section on footnotes for details on how to mark up footnotes. 
  */
@SuppressWarnings("unchecked")
class _HtmlDetailsBuilder<self_t extends IHtmlTagBuilder<?>>
        extends DecoratedHtmlTagBuilder<self_t> {

    public _HtmlDetailsBuilder(IXmlTagBuilder o) {
        super(o);
    }

    /**
      * The open content attribute is a boolean attribute. If present, it indicates that both the summary and the additional information is to be shown to the user. If the attribute is absent, only the summary is to be shown. 
      */
    public self_t open(String val) {
        attr("open", val);
        return (self_t) this;
    }

}
