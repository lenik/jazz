package net.bodz.bas.fmt.xml.xq;

import org.w3c.dom.Attr;

public class QAttrs
        extends AbstractXmlSelection {

    Iterable<Attr> attrs;

    public QAttrs(Iterable<Attr> attrs) {
        this.attrs = attrs;
    }

    @Override
    public String getVarSource() {
        StringBuilder sb = null;
        for (Attr attr : attrs) {
            String content = attr.getValue();
            if (sb == null)
                sb = new StringBuilder(content);
            else
                sb.append(content);
        }
        return sb == null ? null : sb.toString();
    }

    @Override
    public IXmlSelection a(String attributeName) {
        return EMPTY;
    }

}
