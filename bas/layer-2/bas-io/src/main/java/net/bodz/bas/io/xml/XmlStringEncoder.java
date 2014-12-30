package net.bodz.bas.io.xml;

/**
 * Escape special chars using XML entities.
 * <p>
 * See <a href="http://en.wikipedia.org/wiki/List_of_XML_and_HTML_character_entity_references">List
 * of XML and HTML character entity references</a>
 */
public class XmlStringEncoder {

    private String[] entityTab = new String[256];

    XmlStringEncoder(String[] tab) {
        if (tab == null)
            throw new NullPointerException("tab");
        this.entityTab = tab;
    }

    static String[] simpleTextTab;
    static String[] simpleAttributeTab;
    static String[] simpleNumericTab;
    static {
        simpleTextTab = new String[256];
        simpleTextTab['&'] = "&amp;";
        simpleTextTab['<'] = "&lt;";
        simpleTextTab['>'] = "&gt;";

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

    public static XmlStringEncoder forAttribute() {
        XmlStringEncoder escaper = new XmlStringEncoder(simpleAttributeTab);
        return escaper;
    }

    public static XmlStringEncoder forText() {
        XmlStringEncoder escaper = new XmlStringEncoder(simpleTextTab);
        return escaper;
    }

    public String encode(String str) {
        if (str == null)
            throw new NullPointerException("str");
        StringBuilder sb = new StringBuilder(str.length() * 5 / 4);
        int len = str.length();
        for (int i = 0; i < len; i++) {
            char ch = str.charAt(i);
            if (ch < entityTab.length) {
                String t = entityTab[ch];
                if (t != null) {
                    sb.append(t);
                    continue;
                }
            }
            sb.append(ch);
        }
        return sb.toString();
    }

}
