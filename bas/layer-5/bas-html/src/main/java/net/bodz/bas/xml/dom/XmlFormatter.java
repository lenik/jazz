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

    public static String NULL_VERBATIM = "<i>(null)</i>";

    private ITreeOut out;
    private XmlStringEncoder attribEncoder = XmlStringEncoder.forAttribute();
    private XmlStringEncoder textEncoder = XmlStringEncoder.forText();

    private boolean selfClosing = true;
    private String nullVerbatim = NULL_VERBATIM;

    public XmlFormatter(ICharOut out) {
        this(TreeOutImpl.from(out));
    }

    public XmlFormatter(ITreeOut out) {
        if (out == null)
            throw new NullPointerException("out");
        this.out = out;
    }

    public boolean isSelfClosing() {
        return selfClosing;
    }

    public void setSelfClosing(boolean selfClosing) {
        this.selfClosing = selfClosing;
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
                Collection<? extends IXmlNode> children = tag.getChildren();

                // no child element and no child text.
                boolean closeable = children.isEmpty() && (selfClosing || tag.isVoid());

                if (!pseudo) {
                    StringBuilder stbuf = new StringBuilder();
                    stbuf.append('<');
                    stbuf.append(tag.getTagName());

                    Map<String, String> attributeMap = tag.getAttributeMap();
                    if (!attributeMap.isEmpty())
                        for (Entry<String, String> entry : attributeMap.entrySet()) {
                            stbuf.append(' ');
                            stbuf.append(entry.getKey());
                            stbuf.append("=\"");
                            stbuf.append(attribEncoder.encode(entry.getValue()));
                            stbuf.append('"');
                        }

                    if (closeable) {
                        stbuf.append("/>");
                        out.println(stbuf.toString());
                    } else {
                        stbuf.append(">");
                        out.print(stbuf.toString());
                        out.enter();
                    }
                }

                for (IXmlNode child : children) {
                    format(child);
                }

                if (!pseudo)
                    if (!closeable) {
                        out.leave();
                        StringBuilder etbuf = new StringBuilder();
                        etbuf.append("</");
                        etbuf.append(tag.getTagName());
                        etbuf.append(">");
                        out.println(etbuf.toString());
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
