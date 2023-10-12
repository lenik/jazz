package net.bodz.bas.doc.word.xwpf;

import java.util.List;

import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;

public class XwTableCell
        extends AbstractXwNode
        implements
            IXwHavePars {

    XWPFTableCell element;

    public XwTableCell(XWPFTableCell element) {
        this(null, element);
    }

    public XwTableCell(IXwNode parent, XWPFTableCell element) {
        super(parent);
        this.element = element;
    }

    @Override
    public XwNodeType getType() {
        return XwNodeType.TABLE_CELL;
    }

    @Override
    public XWPFTableCell getElement() {
        return element;
    }

    @Override
    public XwPar addPar() {
        XWPFParagraph _par = element.addParagraph();
        return new XwPar(_par);
    }

    @Override
    public void addPlainText(String text) {
        List<XWPFParagraph> pars = element.getParagraphs();
        XWPFParagraph lastPar;
        if (pars.isEmpty())
            lastPar = element.addParagraph();
        else
            lastPar = pars.get(pars.size() - 1);

        XwUtils.addPlainText(lastPar, text);
    }

}
