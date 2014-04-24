package net.bodz.bas.io.html.tag;

import net.bodz.bas.io.html.DecoratedHtmlTagBuilder;
import net.bodz.bas.io.html.IHtmlTagBuilder;
import net.bodz.bas.io.xml.IXmlTagBuilder;

/**
  * The html element in the following example declares that the document's language is English. 
  */
@SuppressWarnings("unchecked")
class _HtmlHtmlBuilder<self_t extends IHtmlTagBuilder<?>>
        extends DecoratedHtmlTagBuilder<self_t> {

    public _HtmlHtmlBuilder(IXmlTagBuilder o) {
        super(o);
    }

    /**
      * The manifest attribute only has an effect during the early stages of document load. Changing the attribute dynamically thus has no effect (and thus, no DOM API is provided for this attribute). 
      */
    public self_t manifest(String val) {
        attr("manifest", val);
        return (self_t) this;
    }

}
