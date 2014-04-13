package net.bodz.bas.io.xml;

public class XmlOutputFormat {

    private boolean xmlDeclaration = true;
    private boolean xhtmlMode = false;
    private String encoding = "utf-8";
    private boolean omitEncoding = false;

    private boolean emptyElement = false;
    private int indentSize = 2;
    private String lineSeparator = "\n";
    private char quoteChar = '\"';

    private boolean stripComment = false;
    private boolean preserveSpace = false;

    public boolean isXmlDeclaration() {
        return xmlDeclaration;
    }

    public void setXmlDeclaration(boolean xmlDeclaration) {
        this.xmlDeclaration = xmlDeclaration;
    }

    public boolean isXhtmlMode() {
        return xhtmlMode;
    }

    public void setXhtmlMode(boolean xhtmlMode) {
        this.xhtmlMode = xhtmlMode;
    }

    public String getEncoding() {
        return encoding;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    public boolean isOmitEncoding() {
        return omitEncoding;
    }

    public void setOmitEncoding(boolean omitEncoding) {
        this.omitEncoding = omitEncoding;
    }

    public boolean isEmptyElement() {
        return emptyElement;
    }

    public void setEmptyElement(boolean emptyElement) {
        this.emptyElement = emptyElement;
    }

    public char getQuoteChar() {
        return quoteChar;
    }

    public void setQuoteChar(char quoteChar) {
        this.quoteChar = quoteChar;
    }

    public int getIndentSize() {
        return indentSize;
    }

    public void setIndentSize(int indentSize) {
        this.indentSize = indentSize;
    }

    public String getLineSeparator() {
        return lineSeparator;
    }

    public void setLineSeparator(String lineSeparator) {
        this.lineSeparator = lineSeparator;
    }

    public boolean isStripComment() {
        return stripComment;
    }

    public void setStripComment(boolean stripComment) {
        this.stripComment = stripComment;
    }

    public boolean isPreserveSpace() {
        return preserveSpace;
    }

    public void setPreserveSpace(boolean preserveSpace) {
        this.preserveSpace = preserveSpace;
    }

    public static final XmlOutputFormat DEFAULT;
    static {
        DEFAULT = new XmlOutputFormat();
    }

}
