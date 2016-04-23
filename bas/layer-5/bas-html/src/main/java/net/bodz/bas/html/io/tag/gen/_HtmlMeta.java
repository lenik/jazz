package net.bodz.bas.html.io.tag.gen;

import net.bodz.bas.html.io.HtmlDoc;
import net.bodz.bas.html.io.AbstractRecHtmlOut;

/**
 * The meta element can represent document-level metadata with the name attribute, pragma directives
 * with the http-equiv attribute, and the file's character encoding declaration when an HTML
 * document is serialized to string form (e.g. for transmission over the network or for disk
 * storage) with the charset attribute.
 */
public class _HtmlMeta<self_t extends AbstractRecHtmlOut<self_t>>
        extends AbstractRecHtmlOut<self_t> {

    public _HtmlMeta(HtmlDoc doc) {
        super(doc);
    }

    public self_t name(Object val) {
        return attr("name", val);
    }

    public self_t httpEquiv(Object val) {
        return attr("http-equiv", val);
    }

    /**
     * The content attribute gives the value of the document metadata or pragma directive when the
     * element is used for those purposes. The allowed values depend on the exact context, as
     * described in subsequent sections of this specification.
     */
    public self_t content(Object val) {
        return attr("content", val);
    }

    /**
     * The charset attribute on the meta element has no effect in XML documents, and is only allowed
     * in order to facilitate migration to and from XHTML.
     */
    public self_t charset(Object val) {
        return attr("charset", val);
    }

}
