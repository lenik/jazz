package net.bodz.bas.doc.word.xwpf;

import org.apache.poi.xwpf.usermodel.XWPFRun;

import net.bodz.bas.t.variant.IVariant;

public class XwRun
        extends AbstractXwNode {

    XWPFRun element;

    public XwRun(XWPFRun element) {
        this(null, element);
    }

    public XwRun(IXwNode parent, XWPFRun element) {
        super(parent);
        this.element = element;
    }

    @Override
    public XwNodeType getType() {
        return XwNodeType.RUN;
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
