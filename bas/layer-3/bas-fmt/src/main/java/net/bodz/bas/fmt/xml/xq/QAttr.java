package net.bodz.bas.fmt.xml.xq;

import org.w3c.dom.Attr;

public class QAttr
        extends AbstractXmlSelection {

    Attr attr;

    public QAttr(Attr attr) {
        this.attr = attr;
    }

    @Override
    public String getVarSource() {
        if (attr == null)
            return null;
        else
            return attr.getValue();
    }

    @Override
    public IXmlSelection a(String attributeName) {
        return EMPTY;
    }

}
