package net.bodz.bas.doc.word.xwpf;

import org.apache.poi.xwpf.usermodel.XWPFRun;

import net.bodz.bas.t.variant.IVariant;

public class XwpfRunNode
        extends AbstractXwpfNode {

    XWPFRun element;

    public XwpfRunNode(XWPFRun element) {
        this(null, element);
    }

    public XwpfRunNode(IXwpfNode parent, XWPFRun element) {
        super(parent);
        this.element = element;
    }

    @Override
    public XwpfNodeType getType() {
        return XwpfNodeType.RUN;
    }

    @Override
    public boolean isRun() {
        return true;
    }

    @Override
    public XWPFRun getElement() {
        return element;
    }

    @Override
    public boolean attribute(String name, IVariant value) {
        // element.setColor(name);
        return super.attribute(name, value);
    }

}
