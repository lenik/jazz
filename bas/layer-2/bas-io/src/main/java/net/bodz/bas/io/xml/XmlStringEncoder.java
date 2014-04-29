package net.bodz.bas.io.xml;

import java.io.IOException;

/**
 * Escape special chars using XML entities.
 * <p>
 * See <a href="http://en.wikipedia.org/wiki/List_of_XML_and_HTML_character_entity_references">List
 * of XML and HTML character entity references</a>
 */
public class XmlStringEncoder {

    private final Appendable out;

    private String[] entityTab = new String[256];

    XmlStringEncoder(Appendable out, String[] tab) {
        if (out == null)
            throw new NullPointerException("out");
        if (tab == null)
            throw new NullPointerException("tab");
        this.out = out;
        this.entityTab = tab;
    }

    static String[] simpleTextTab;
    static String[] simpleAttributeTab;
    static String[] simpleNumericTab;
    static {
        simpleTextTab = new String[256];
        simpleTextTab['&'] = "&amp;";
        simpleTextTab['<'] = "&lt;";
        simpleTextTab['>'] = "&rt;";

        simpleAttributeTab = new String[256];
        simpleAttributeTab['\"'] = "&quot;";
        simpleAttributeTab['&'] = "&amp;";
        simpleAttributeTab['\''] = "&apos;";

        simpleNumericTab = new String[256];
        simpleNumericTab['\"'] = "&#34;";
        simpleNumericTab['&'] = "&#38;";
        simpleNumericTab['\''] = "&#;39";
        simpleNumericTab['<'] = "&#60;";
        simpleNumericTab['>'] = "&#62;";
    }

    public static XmlStringEncoder forAttribute(Appendable out) {
        XmlStringEncoder escaper = new XmlStringEncoder(out, simpleAttributeTab);
        return escaper;
    }

    public static XmlStringEncoder forText(Appendable out) {
        XmlStringEncoder escaper = new XmlStringEncoder(out, simpleTextTab);
        return escaper;
    }

    public void encode(String str)
            throws IOException {
        int len = str.length();
        for (int i = 0; i < len; i++) {
            char ch = str.charAt(i);
            if (ch < entityTab.length) {
                String t = entityTab[ch];
                if (t != null) {
                    out.append(t);
                    continue;
                }
            }
            out.append(ch);
        }
    }

    @Override
    public String toString() {
        return out.toString();
    }

}