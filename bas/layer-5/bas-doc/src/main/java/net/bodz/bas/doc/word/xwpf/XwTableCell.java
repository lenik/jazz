package net.bodz.bas.doc.word.xwpf;

import java.util.List;

import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;

public class XwTableCell
        extends AbstractXwNode
        implements
            IXwHavePars {

    XWPFTableCell cell;

    public XwTableCell(XWPFTableCell element) {
        this(null, element);
    }

    public XwTableCell(IXwNode parent, XWPFTableCell element) {
        super(parent);
        this.cell = element;
    }

    @Override
    public XwNodeType getType() {
        return XwNodeType.TABLE_CELL;
    }

    @Override
    public XWPFTableCell getElement() {
        return cell;
    }

    @Override
    public XwPar addPar() {
        XWPFParagraph _par = cell.addParagraph();
        return new XwPar(this, _par);
    }

    @Override
    public XwPar getParToAppend() {
        List<XWPFParagraph> pars = cell.getParagraphs();
        if (pars.isEmpty())
            return addPar();
        XWPFParagraph lastPar = pars.get(pars.size() - 1);
        return new XwPar(this, lastPar);
    }

    @Override
    public void addPlainText(String text) {
        List<XWPFParagraph> pars = cell.getParagraphs();
        XWPFParagraph lastPar;
        if (pars.isEmpty())
            lastPar = cell.addParagraph();
        else
            lastPar = pars.get(pars.size() - 1);

        XwUtils.addPlainText(lastPar, text);
    }

}
