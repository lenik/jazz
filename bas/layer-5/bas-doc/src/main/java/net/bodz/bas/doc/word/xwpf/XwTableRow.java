package net.bodz.bas.doc.word.xwpf;

import javax.xml.stream.XMLStreamException;

import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTOnOff;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRow;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTrPr;

import net.bodz.bas.err.FormatException;
import net.bodz.bas.err.LoaderException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.xml.IXmlOutput;
import net.bodz.bas.fmt.xml.xq.IElement;

public class XwTableRow
        extends AbstractXwNode {

    XWPFTableRow element;
    public int cptr;

    public XwTableRow(XWPFTableRow element) {
        this(null, element);
    }

    public XwTableRow(IXwNode parent, XWPFTableRow element) {
        super(parent);
        this.element = element;
    }

    @Override
    public XwNodeType getType() {
        return XwNodeType.TABLE_ROW;
    }

    @Override
    public XWPFTableRow getElement() {
        return element;
    }

    public XwTableCell addCell() {
        XWPFTableCell _cell = element.createCell();
        XwTableCell cell = new XwTableCell(_cell);
        return cell;
    }

    @Override
    public void readObject(IElement element)
            throws ParseException, LoaderException {
    }

    @Override
    public void writeObject(IXmlOutput out)
            throws XMLStreamException, FormatException {
        CTRow ctRow = element.getCtRow();
        out.beginElement("Tr");

        CTTrPr trPr = ctRow.getTrPr();
        if (trPr != null) {
            out.beginElement("Pr");

            CTOnOff[] tblHeaders = trPr.getTblHeaderArray();
            for (int i = 0; i < tblHeaders.length; i++) {
                CTOnOff tblHeader = tblHeaders[i];
                out.attributeNotNull("hdr" + i, tblHeader.getVal());
            }

            out.endElement();
        }

        for (XWPFTableCell _cell : element.getTableCells()) {
            XwTableCell cell = new XwTableCell(this, _cell);
            cell.writeObject(out);
        }

        out.endElement();
    }

}
