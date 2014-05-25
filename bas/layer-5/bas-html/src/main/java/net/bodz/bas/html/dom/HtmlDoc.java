package net.bodz.bas.html.dom;

import net.bodz.bas.html.dom.tag.HtmlHtmlTag;
import net.bodz.bas.xml.dom.DtdTag;

public class HtmlDoc
        extends AbstractHtmlTag<HtmlDoc> {

    private HtmlDocType docType;
    private HtmlHtmlTag root;

    public HtmlDoc() {
        this(HtmlDocType.HTML_5);
    }

    public HtmlDoc(HtmlDocType docType) {
        super(null);
        if (docType == null)
            throw new NullPointerException("docType");
        this.docType = docType;

        String publicId = docType.getPublicId();
        if (publicId != null)
            doctype(docType.getPublicId(), docType.getSystemId());

        root = html();
        String ns = docType.getNamespace();
        if (ns != null)
            root.attr("xmlns", docType.getNamespace());
    }

    public HtmlDocType getDocType() {
        return docType;
    }

    void doctype(String publicId, String systemId) {
        DtdTag docType = new DtdTag(this, "DOCTYPE");
        docType.setContent("html PUBLIC \"" + publicId + "\" \"" + systemId + "\"");
    }

    public HtmlHtmlTag getRoot() {
        return root;
    }

}
