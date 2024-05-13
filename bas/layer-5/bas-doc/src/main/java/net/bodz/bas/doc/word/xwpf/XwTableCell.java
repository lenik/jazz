package net.bodz.bas.doc.word.xwpf;

import java.util.List;

import javax.xml.stream.XMLStreamException;

import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDecimalNumber;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTHMerge;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTc;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTVMerge;

import net.bodz.bas.err.FormatException;
import net.bodz.bas.err.LoaderException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.xml.IXmlOutput;
import net.bodz.bas.fmt.xml.xq.IElement;

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

    @Override
    public void readObject(IElement element)
            throws ParseException, LoaderException {
    }

    @Override
    public void writeObject(IXmlOutput out)
            throws XMLStreamException, FormatException {
        CTTc ctTc = cell.getCTTc();
        out.beginElement("Tc");

        CTTcPr tcPr = ctTc.getTcPr();
        if (tcPr != null) {
            CTDecimalNumber gridSpan = tcPr.getGridSpan();
            if (gridSpan != null)
                out.attributeNotNull("gridSpan", gridSpan.getVal());

            CTHMerge hMerge = tcPr.getHMerge();
            if (hMerge != null)
                out.attributeNotNull("hMerge", hMerge.getVal());

            CTVMerge vMerge = tcPr.getVMerge();
            if (vMerge != null)
                out.attributeNotNull("vMerge", vMerge.getVal());
        }

        for (XWPFParagraph _par : cell.getParagraphs()) {
            XwPar par = new XwPar(this, _par);
            par.writeObject(out);
        }

        out.endElement();
    }

}
