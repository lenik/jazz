package net.bodz.bas.io.xml;

import java.io.IOException;

/**
 * See <a href="http://en.wikipedia.org/wiki/List_of_XML_and_HTML_character_entity_references">List
 * of XML and HTML character entity references</a>
 */
public class XmlStringEncoder {

    private final Appendable out;

    private String[] tab = new String[256];

    XmlStringEncoder(Appendable out, String[] tab) {
        if (out == null)
            throw new NullPointerException("out");
        if (tab == null)
            throw new NullPointerException("tab");
        this.out = out;
        this.tab = tab;
    }

    static String[] simpleHtmlTab;
    static String[] simpleNumericTab;
    static {
        simpleHtmlTab = new String[256];
        simpleHtmlTab['\"'] = "&quot;";
        simpleHtmlTab['&'] = "&amp;";
        simpleHtmlTab['\''] = "&apos;";
        simpleHtmlTab['<'] = "&lt;";
        simpleHtmlTab['>'] = "&rt;";

        simpleNumericTab = new String[256];
        simpleNumericTab['\"'] = "&#34;";
        simpleNumericTab['&'] = "&#38;";
        simpleNumericTab['\''] = "&#;39";
        simpleNumericTab['<'] = "&#60;";
        simpleNumericTab['>'] = "&#62;";

        for (int i = 0; i < 32; i++)
            simpleHtmlTab[i] = simpleNumericTab[i] = "&#" + i + ";";
    }

    public static XmlStringEncoder forAttribute(Appendable out) {
        XmlStringEncoder escaper = new XmlStringEncoder(out, simpleHtmlTab);
        return escaper;
    }

    public static XmlStringEncoder forText(Appendable out) {
        XmlStringEncoder escaper = new XmlStringEncoder(out, simpleNumericTab);
        return escaper;
    }

    public void encode(String str)
            throws IOException {
        int len = str.length();
        for (int i = 0; i < len; i++) {
            char ch = str.charAt(i);
            if (ch < tab.length) {
                String t = tab[ch];
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
