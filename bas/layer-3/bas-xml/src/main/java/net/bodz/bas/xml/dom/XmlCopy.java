package net.bodz.bas.xml.dom;

import net.bodz.bas.io.xml.IXmlOut;

public class XmlCopy {

    public static void copy(IXmlNode node, IXmlOut out, boolean extract) {
        switch (node.getType()) {
        case DTD_TAG:
            DtdTag dtdTag = (DtdTag) node;
            out.dtd(dtdTag.getTagName(), dtdTag.getContent());
            break;

        case COMMENT:
            XmlComment comment = (XmlComment) node;
            out.comment(comment.getContent());
            break;

        case ELEMENT:
            IXmlTag tag = (IXmlTag) node;
            IXmlOut sub = out;

            if (!extract) {
                sub = out.begin(tag.getTagName());
                sub.attrs(tag.getAttributeMap());
            }

            for (IXmlNode child : tag.getChildren())
                copy(child, sub, false);

            if (!extract)
                sub.end();
            break;

        case TEXT:
            XmlText text = (XmlText) node;
            String content = text.getContent();
            if (content == null) {
                String nullVerbatim = text.getNullVerbatim();
                if (nullVerbatim != null)
                    out.verbatim(nullVerbatim);
            } else {
                out.text(content);
            }
            break;

        case CDATA:
            XmlCData cData = (XmlCData) node;
            out.cdata(cData.getContent());
            break;

        case PROCESS_INSTRUCTION:
            XmlPI pi = (XmlPI) node;
            out.pi(pi.getTarget(), pi.getContent());
            break;

        case VERBATIM:
            XmlVerbatim verbatim = (XmlVerbatim) node;
            out.verbatim(verbatim.getContent());
            break;

        default:
        }
    }

}
