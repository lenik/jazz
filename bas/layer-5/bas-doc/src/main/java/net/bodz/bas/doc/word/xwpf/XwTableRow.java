package net.bodz.bas.doc.word.xwpf;

import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

public class XwTableRow
        extends AbstractXwNode {

    XWPFTableRow element;

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

}
