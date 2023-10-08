package net.bodz.bas.doc.word.xwpf;

import org.apache.poi.xwpf.usermodel.XWPFDocument;

import net.bodz.bas.t.variant.IVariant;

public class XwpfDocNode
        extends AbstractXwpfNode {

    XWPFDocument element;

    public XwpfDocNode(XWPFDocument element) {
        this(null, element);
    }

    public XwpfDocNode(IXwpfNode parent, XWPFDocument element) {
        super(parent);
        this.element = element;
    }

    @Override
    public XwpfDocNode getDocument() {
        return this;
    }

    @Override
    public XwpfNodeType getType() {
        return XwpfNodeType.DOCUMENT;
    }

    @Override
    public XWPFDocument getElement() {
        return element;
    }

    @Override
    public boolean isPars() {
        return true;
    }

    static final String TRACK_REVISIONS = "trackRevisions";

    @Override
    public boolean attribute(String name, IVariant value) {
        switch (name) {
        case TRACK_REVISIONS:
            element.setTrackRevisions(value.getBoolean());
            return true;
        }
        return false;
    }

}
