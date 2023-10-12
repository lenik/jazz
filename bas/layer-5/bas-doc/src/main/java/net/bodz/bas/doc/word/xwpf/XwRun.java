package net.bodz.bas.doc.word.xwpf;

import org.apache.poi.xwpf.usermodel.XWPFRun;

import net.bodz.bas.t.variant.IVariant;

public class XwRun
        extends AbstractXwNode {

    XWPFRun element;

    public XwRun(XwPar parent, XWPFRun element) {
        super(parent);
        this.element = element;
    }

    @Override
    public XwNodeType getType() {
        return XwNodeType.RUN;
    }

    @Override
    public XwPar getParent() {
        return (XwPar) super.getParent();
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
