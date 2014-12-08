package net.bodz.bas.html.dom;

public enum HtmlDocType {

    HTML_5(null, null),

    /**
     * HTML 4.01 Strict
     * <p>
     * This DTD contains all HTML elements and attributes, but does NOT INCLUDE presentational or
     * deprecated elements (like font). Framesets are not allowed.
     */
    HTML_4_01_Strict("-//W3C//DTD HTML 4.01//EN", "http://www.w3.org/TR/html4/strict.dtd"),

    /**
     * HTML 4.01 Transitional
     * <p>
     * This DTD contains all HTML elements and attributes, INCLUDING presentational and deprecated
     * elements (like font). Framesets are not allowed.
     */
    HTML_4_01_Transitional("-//W3C//DTD HTML 4.01 Transitional//EN", "http://www.w3.org/TR/html4/loose.dtd"),

    /**
     * HTML 4.01 Frameset
     * <p>
     * This DTD is equal to HTML 4.01 Transitional, but allows the use of frameset content.
     */
    HTML_4_01_Frameset("-//W3C//DTD HTML 4.01 Frameset//EN", "http://www.w3.org/TR/html4/frameset.dtd"),

    /**
     * XHTML 1.0 Strict
     * <p>
     * This DTD contains all HTML elements and attributes, but does NOT INCLUDE presentational or
     * deprecated elements (like font). Framesets are not allowed. The markup must also be written
     * as well-formed XML.
     */
    XHTML_1_0_Strict("-//W3C//DTD XHTML 1.0 Strict//EN", "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd",
            "http://www.w3.org/1999/xhtml"),

    /**
     * XHTML 1.0 Transitional
     * <p>
     * This DTD contains all HTML elements and attributes, INCLUDING presentational and deprecated
     * elements (like font). Framesets are not allowed. The markup must also be written as
     * well-formed XML.
     */
    XHTML_1_0_Transitional("-//W3C//DTD XHTML 1.0 Transitional//EN",
            "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd", "http://www.w3.org/1999/xhtml"),

    /**
     * XHTML 1.0 Frameset
     * <p>
     * This DTD is equal to XHTML 1.0 Transitional, but allows the use of frameset content.
     */
    XHTML_1_0_Frameset("-//W3C//DTD XHTML 1.0 Frameset//EN", "http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd",
            "http://www.w3.org/1999/xhtml"),

    /**
     * XHTML 1.1
     * <p>
     * This DTD is equal to XHTML 1.0 Strict, but allows you to add modules (for example to provide
     * ruby support for East-Asian languages).
     */
    XHTML_1_1("-//W3C//DTD XHTML 1.1//EN", "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd",
            "http://www.w3.org/1999/xhtml"),

    ;

    private String publicId;
    private String systemId;
    private String namespace;

    private HtmlDocType(String publicId, String systemId) {
        this.publicId = publicId;
        this.systemId = systemId;
    }

    private HtmlDocType(String publicId, String systemId, String namespace) {
        this.publicId = publicId;
        this.systemId = systemId;
        this.namespace = namespace;
    }

    public String getPublicId() {
        return publicId;
    }

    public String getSystemId() {
        return systemId;
    }

    public String getNamespace() {
        return namespace;
    }

    public boolean isXhtml() {
        switch (this) {
        case XHTML_1_0_Frameset:
        case XHTML_1_0_Strict:
        case XHTML_1_0_Transitional:
        case XHTML_1_1:
            return true;
        default:
            return false;
        }
    }

}
