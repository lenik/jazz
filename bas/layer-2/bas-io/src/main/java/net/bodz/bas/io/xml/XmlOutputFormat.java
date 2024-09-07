package net.bodz.bas.io.xml;

import java.util.function.Predicate;

public class XmlOutputFormat {

    public boolean xmlDeclaration = true;
    public String encoding = "utf-8";
    public boolean omitEncoding = false;

    public int indentSize = 2;
    public String lineSeparator = "\n";
    public char quoteChar = '\"';
    public String nullText = "(null)";

    public boolean stripComment = false;
    public boolean preserveSpace = false;

    /**
     * <ul>
     * <li><code>true</code> for <code>&lt;foo /&gt;</code>.
     * <li><code>false</code> for <code>&lt;foo&gt;&lt;/foo&gt;</code>
     * </ul>
     */
    public boolean shortEmptyElement = false;

    public boolean newLineAfterStartTag = false;
    public boolean newLineBeforeEndTag = false;

    public boolean newLineAfterComment = true;
    public boolean newLineAfterDtd = true;
    public boolean newLineAfterPI = true;
    public boolean newLineAfterVerbatim = false;

    public boolean attributeBuffer = true;
    public Boolean sortAttributeNames = false;

    XmlStringEncoder attribEncoder = XmlStringEncoder.forAttribute();
    XmlStringEncoder textEncoder = XmlStringEncoder.forText();

    Predicate<IXmlOut> shouldBreakLine;

    public String encodeAttr(String str) {
        return attribEncoder.encode(str);
    }

    public String encodeText(String str) {
        return textEncoder.encode(str);
    }

    public static final XmlOutputFormat DEFAULT;
    static {
        DEFAULT = new XmlOutputFormat();
    }

}
