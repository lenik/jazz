package net.bodz.bas.doc.word.xwpf;

import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

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

}
