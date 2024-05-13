package net.bodz.bas.doc.word.xwpf;

import javax.xml.stream.XMLStreamException;

import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPr;

import net.bodz.bas.err.FormatException;
import net.bodz.bas.err.LoaderException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.xml.IXmlOutput;
import net.bodz.bas.fmt.xml.xq.IElement;

public class XwTable
        extends AbstractXwNode {

    XWPFTable element;

    public XwTable(XWPFTable element) {
        this(null, element);
    }

    public XwTable(IXwNode parent, XWPFTable element) {
        super(parent);
        this.element = element;
    }

    @Override
    public XwNodeType getType() {
        return XwNodeType.TABLE;
    }

    @Override
    public XWPFTable getElement() {
        return element;
    }

    public XwTableRow addRow() {
        XWPFTableRow _row = element.createRow();
        XwTableRow row = new XwTableRow(_row);
        return row;
    }

    @Override
    public void readObject(IElement element)
            throws ParseException, LoaderException {
    }

    @Override
    public void writeObject(IXmlOutput out)
            throws XMLStreamException, FormatException {
        CTTbl ctTbl = element.getCTTbl();
        out.beginElement("Tbl");

        CTTblPr tblPr = ctTbl.getTblPr();
        if (tblPr != null) {
            out.beginElement("Pr");
            out.endElement();
        }

        for (XWPFTableRow _row : element.getRows()) {
            XwTableRow row = new XwTableRow(_row);
            row.writeObject(out);
        }

        out.endElement();
    }

}
