package net.bodz.bas.doc.word.xwpf;

import org.apache.poi.xwpf.usermodel.XWPFTableCell;

public class XwpfTableCellNode
        extends AbstractXwpfNode {

    XWPFTableCell element;

    public XwpfTableCellNode(XWPFTableCell element) {
        this(null, element);
    }

    public XwpfTableCellNode(IXwpfNode parent, XWPFTableCell element) {
        super(parent);
        this.element = element;
    }

    @Override
    public XwpfNodeType getType() {
        return XwpfNodeType.TABLE_CELL;
    }

    @Override
    public boolean isPars() {
        return true;
    }

    @Override
    public XWPFTableCell getElement() {
        return element;
    }

}
