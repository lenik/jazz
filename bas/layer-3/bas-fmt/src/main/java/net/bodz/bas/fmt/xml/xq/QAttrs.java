package net.bodz.bas.fmt.xml.xq;

import org.w3c.dom.Attr;

public class QAttrs
        extends AbstractXmlSelection {

    ICachedIterable<Attr> attrs;

    public QAttrs(Iterable<Attr> attrs) {
        this.attrs = DefaultCachedIterable.wrap(attrs);
    }

    @Override
    public int getNodeCount() {
        return attrs.size();
    }

    @Override
    public int getElementCount() {
        return 0;
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
