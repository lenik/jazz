package net.bodz.bas.doc.word.xwpf;

import org.apache.poi.xwpf.usermodel.XWPFParagraph;

public class XwpfParNode
        extends AbstractXwpfNode {

    XWPFParagraph element;

    public XwpfParNode(XWPFParagraph element) {
        this(null, element);
    }

    public XwpfParNode(IXwpfNode parent, XWPFParagraph element) {
        super(parent);
        this.element = element;
    }

    @Override
    public XwpfNodeType getType() {
        return XwpfNodeType.PARAGRAPH;
    }

    @Override
    public boolean isPar() {
        return true;
    }

    @Override
    public XWPFParagraph getElement() {
        return element;
    }

}
