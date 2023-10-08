package net.bodz.bas.doc.word.xwpf;

import org.apache.poi.xwpf.usermodel.XWPFTableRow;

public class XwpfTableRowNode
        extends AbstractXwpfNode {

    XWPFTableRow element;

    public XwpfTableRowNode(XWPFTableRow element) {
        this(null, element);
    }

    public XwpfTableRowNode(IXwpfNode parent, XWPFTableRow element) {
        super(parent);
        this.element = element;
    }

    @Override
    public XwpfNodeType getType() {
        return XwpfNodeType.TABLE_ROW;
    }

    @Override
    public XWPFTableRow getElement() {
        return element;
    }

}
