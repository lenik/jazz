package net.bodz.bas.html.dom.tag;

import net.bodz.bas.html.dom.AbstractHtmlTag;
import net.bodz.bas.html.dom.IHtmlTag;

/**
  * The meta element can represent document-level metadata with the name attribute, pragma directives with the http-equiv attribute, and the file's character encoding declaration when an HTML document is serialized to string form (e.g. for transmission over the network or for disk storage) with the charset attribute. 
  */
@SuppressWarnings("unchecked")
class _HtmlMetaTag<self_t extends IHtmlTag>
        extends AbstractHtmlTag<self_t> {

    public _HtmlMetaTag(IHtmlTag parent, String tagName) {
        super(parent, tagName);
    }

    public self_t name(String val) {
        attr("name", val);
        return (self_t) this;
    }

    public self_t httpEquiv(String val) {
        attr("http-equiv", val);
        return (self_t) this;
    }

    /**
      * The content attribute gives the value of the document metadata or pragma directive when the element is used for those purposes. The allowed values depend on the exact context, as described in subsequent sections of this specification. 
      */
    public self_t content(String val) {
        attr("content", val);
        return (self_t) this;
    }

    /**
      * The charset attribute on the meta element has no effect in XML documents, and is only allowed in order to facilitate migration to and from XHTML. 
      */
    public self_t charset(String val) {
        attr("charset", val);
        return (self_t) this;
    }

}
