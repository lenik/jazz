package net.bodz.bas.doc.word.xwpf;

import org.apache.poi.xwpf.usermodel.XWPFTable;

public class XwpfTableNode
        extends AbstractXwpfNode {

    XWPFTable element;

    public XwpfTableNode(XWPFTable element) {
        this(null, element);
    }

    public XwpfTableNode(IXwpfNode parent, XWPFTable element) {
        super(parent);
        this.element = element;
    }

    @Override
    public XwpfNodeType getType() {
        return XwpfNodeType.TABLE;
    }

    @Override
    public XWPFTable getElement() {
        return element;
    }

}
