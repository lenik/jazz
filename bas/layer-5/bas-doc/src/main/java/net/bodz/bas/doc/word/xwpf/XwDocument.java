package net.bodz.bas.doc.word.xwpf;

import java.util.List;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import net.bodz.bas.t.variant.IVariant;

public class XwDocument
        extends AbstractXwNode
        implements
            IXwHavePars {

    XWPFDocument element;

    public XwDocument(XWPFDocument element) {
        this(null, element);
    }

    public XwDocument(IXwNode parent, XWPFDocument element) {
        super(parent);
        this.element = element;
    }

    @Override
    public XwDocument getDocument() {
        return this;
    }

    @Override
    public XwNodeType getType() {
        return XwNodeType.DOCUMENT;
    }

    @Override
    public XWPFDocument getElement() {
        return element;
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

    @Override
    public XwPar addPar() {
        XWPFParagraph _par = element.createParagraph();
        return new XwPar(_par);
    }

    @Override
    public void addPlainText(String text) {
        List<XWPFParagraph> pars = element.getParagraphs();
        XWPFParagraph lastPar;
        if (pars.isEmpty())
            lastPar = element.createParagraph();
        else
            lastPar = pars.get(pars.size() - 1);
        XWPFRun run = lastPar.createRun();
        run.setText(text);
    }

}
