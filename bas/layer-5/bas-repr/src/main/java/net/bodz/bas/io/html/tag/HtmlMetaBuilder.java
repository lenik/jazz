package net.bodz.bas.io.html.tag;

import net.bodz.bas.io.html.DecoratedHtmlTagBuilder;
import net.bodz.bas.io.xml.IXmlTagBuilder;

/**
  * The meta element can represent document-level metadata with the name attribute, pragma directives with the http-equiv attribute, and the file's character encoding declaration when an HTML document is serialized to string form (e.g. for transmission over the network or for disk storage) with the charset attribute. 
  */
public class HtmlMetaBuilder
        extends DecoratedHtmlTagBuilder<HtmlMetaBuilder> {

    public HtmlMetaBuilder(IXmlTagBuilder o) {
        super(o);
    }

    public HtmlMetaBuilder name(String val) {
        attr("name", val);
        return this;
    }

    public HtmlMetaBuilder httpEquiv(String val) {
        attr("http-equiv", val);
        return this;
    }

    /**
      * The content attribute gives the value of the document metadata or pragma directive when the element is used for those purposes. The allowed values depend on the exact context, as described in subsequent sections of this specification. 
      */
    public HtmlMetaBuilder content(String val) {
        attr("content", val);
        return this;
    }

    /**
      * The charset attribute on the meta element has no effect in XML documents, and is only allowed in order to facilitate migration to and from XHTML. 
      */
    public HtmlMetaBuilder charset(String val) {
        attr("charset", val);
        return this;
    }

}
