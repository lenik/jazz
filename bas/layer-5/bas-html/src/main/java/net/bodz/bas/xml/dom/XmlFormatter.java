package net.bodz.bas.xml.dom;

import java.util.Collection;
import java.util.Map;
import java.util.Map.Entry;

import net.bodz.bas.err.UnexpectedException;
import net.bodz.bas.io.ICharOut;
import net.bodz.bas.io.ITreeOut;
import net.bodz.bas.io.impl.TreeOutImpl;
import net.bodz.bas.io.xml.XmlStringEncoder;

public class XmlFormatter {

    private ITreeOut out;
    private XmlStringEncoder attribEncoder = XmlStringEncoder.forAttribute();
    private XmlStringEncoder textEncoder = XmlStringEncoder.forText();

    private String nullVerbatim = "<i>(null)</i>";

    public XmlFormatter(ICharOut out) {
        this(TreeOutImpl.from(out));
    }

    public XmlFormatter(ITreeOut out) {
        if (out == null)
            throw new NullPointerException("out");
        this.out = out;
    }

    public String getNullVerbatim() {
        return nullVerbatim;
    }

    public void setNullVerbatim(String nullVerbatim) {
        this.nullVerbatim = nullVerbatim;
    }

    public void format(IXmlNode node) {
        switch (node.getType()) {
        case ELEMENT:
            IXmlTag tag = (IXmlTag) node;
            {
                boolean pseudo = tag.getTagName() == null;
                StringBuilder sb = new StringBuilder();
                Collection<? extends IXmlNode> children = tag.getChildren();

                if (!pseudo) {
                    sb.append('<');
                    sb.append(tag.getTagName());

                    Map<String, String> attributeMap = tag.getAttributeMap();
                    if (!attributeMap.isEmpty())
                        for (Entry<String, String> entry : attributeMap.entrySet()) {
                            sb.append(' ');
                            sb.append(entry.getKey());
                            sb.append("=\"");
                            sb.append(attribEncoder.encode(entry.getValue()));
                            sb.append('"');
                        }

                    if (children.isEmpty()) {
                        sb.append("/>");
                        out.println(sb.toString());
                    } else {
                        sb.append(">");
                        out.print(sb.toString());
                        out.enter();
                    }
                    sb.setLength(0);
                }

                for (IXmlNode child : children) {
                    format(child);
                }

                if (!pseudo && !children.isEmpty()) {
                    out.leave();
                    sb.append("</");
                    sb.append(tag.getTagName());
                    sb.append(">");
                    out.println(sb.toString());
                }
            }
            break;

        case TEXT:
            XmlText text = (XmlText) node;
            {
                String content = text.getContent();
                if (content == null) {
                    String nv = text.getNullVerbatim();
                    if (nv == null)
                        nv = nullVerbatim;
                    if (nv != null)
                        out.print(nv);
                } else {
                    String encoded = textEncoder.encode(content);
                    out.print(encoded);
                }
            }
            break;

        case PROCESS_INSTRUCTION:
            XmlPI pi = (XmlPI) node;
            {
                StringBuilder sb = new StringBuilder();
                sb.append("<?");
                sb.append(pi.getTarget());
                String content = pi.getContent();
                if (!content.isEmpty()) {
                    sb.append(' ');
                    sb.append(content);
                }
                sb.append("?>");
                out.println(sb.toString());
            }
            break;

        case COMMENT:
            XmlComment comment = (XmlComment) node;
            {
                String content = comment.getContent();
                String encoded = textEncoder.encode(content);
                out.print("<!-- ");
                out.print(encoded);
                out.println("-->");
            }
            break;

        case CDATA:
            XmlCData cData = (XmlCData) node;
            {
                out.print("<!CDATA[[");
                out.println(cData.getContent());
                out.println("]]>");
            }
            break;

        case DTD_TAG:
            DtdTag dtdTag = (DtdTag) node;
            {
                StringBuilder sb = new StringBuilder();
                sb.append("<!");
                sb.append(dtdTag.getTagName());
                sb.append(" ");
                sb.append(dtdTag.getContent());
                sb.append(">");
                out.println(sb.toString());
            }
            break;

        case VERBATIM:
            XmlVerbatim verbatim = (XmlVerbatim) node;
            {
                out.print(verbatim.getContent());
            }
            break;

        default:
            throw new UnexpectedException();
        }
    }

}
