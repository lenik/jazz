package net.bodz.bas.html.dom;

import net.bodz.bas.html.dom.tag.MutableHtml;
import net.bodz.bas.xml.dom.DtdTag;

public class MutableHtmlDoc
        extends MutableHtml {

    private HtmlDocType docType;
    private IHtmlTagMap tagMap = new HtmlTagMap();

    public MutableHtmlDoc() {
        this(HtmlDocType.HTML_5);
    }

    public MutableHtmlDoc(HtmlDocType docType) {
        super(null);

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
    public MutableHtmlDoc getRoot() {
        return this;
    }

    public IHtmlTag getElementById(String id) {
        return tagMap.get(id);
    }

    public IHtmlTagMap getTagMap() {
        return tagMap;
    }

}
