package net.bodz.bas.html.dom;

import net.bodz.bas.html.dom.tag.HtmlHtmlTag;
import net.bodz.bas.xml.dom.DtdTag;

public class HtmlDoc
        extends HtmlHtmlTag {

    private HtmlDocType docType;
    private IHtmlTagMap tagMap = new HtmlTagMap();

    public HtmlDoc() {
        this(HtmlDocType.HTML_5);
    }

    public HtmlDoc(HtmlDocType docType) {
        super(null, "html");

        if (docType == null)
            throw new NullPointerException("docType");
        this.docType = docType;

        switch (docType) {
        case HTML_5:
            verbatim("<!DOCTYPE html>");
            break;
        default:
            doctype(docType.getPublicId(), docType.getSystemId());
        }

        if (docType.isXhtml()) {
            String ns = docType.getNamespace();
            assert ns != null;
            attr("xmlns", ns);
        }
    }

    public HtmlDocType getDocType() {
        return docType;
    }

    void doctype(String publicId, String systemId) {
        DtdTag docType = new DtdTag(this, "DOCTYPE");
        docType.setContent("html PUBLIC \"" + publicId + "\" \"" + systemId + "\"");
    }

    @Override
    public HtmlHtmlTag getRoot() {
        return this;
    }

    public IHtmlTagMap getTagMap() {
        return tagMap;
    }

}
