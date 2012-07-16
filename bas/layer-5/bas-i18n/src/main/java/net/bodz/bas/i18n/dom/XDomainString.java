package net.bodz.bas.i18n.dom;

import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class XDomainString
        extends XDomainNode<XDomainString, String>
        implements DomainString, Cloneable {

    public XDomainString() {
        super(null, null);
    }

    public XDomainString(String value) {
        super(null, value);
    }

    public XDomainString(String domain, String value) {
        super(domain, value);
    }

    public XDomainString(String domain, String value, XDomainString... follows) {
        super(domain, value, follows);
    }

    protected XDomainString(XDomainString other) {
        super(other);
    }

    @Override
    public XDomainString clone() {
        return new XDomainString(this);
    }

    @Override
    protected XDomainString createNode(String domain, String value) {
        return new XDomainString(domain, value);
    }

    @Override
    public DomainString append(DomainString other) {
        _join(other, false, this);
        return this;
    }

    @Override
    public XDomainString concat(DomainString other) {
        XDomainString out = clone();
        _join(other, false, out);
        return out;
    }

    @Override
    public XDomainString join(DomainString other) {
        XDomainString out = clone();
        _join(other, true, out);
        return out;
    }

    void _join(DomainString other, boolean bestFits, XDomainString output) {
        if (other == null)
            throw new NullPointerException("other");

        if (bestFits) { // find which domains are occurred in this only.
            for (Entry<String, XDomainString> entry : this) {
                String d1 = entry.getKey();
                XDomainString node1 = entry.getValue();
                if (node1.value == null)
                    continue;
                if (other.get(d1) != null)
                    continue;

                String fallback2 = other.getNearest(d1);
                if (fallback2 != null)
                    if (output == this)
                        node1.value += fallback2;
                    else
                        output.put(d1, node1.value + fallback2);
            }
        }

        for (Entry<String, String> entry : other.entrySet()) {
            String d2 = entry.getKey();
            String s2 = entry.getValue();
            if (s2 == null)
                continue;

            String fallback1 = null;
            if (bestFits)
                fallback1 = getNearest(d2);

            XDomainString outNode = output.create(d2, fallback1);
            if (outNode.value == null)
                outNode.value = s2;
            else
                outNode.value += s2;
        }
    }

    /**
     * Get locale string.
     */
    @Override
    public String toString() {

        return value;
    }

    @Override
    public String toParaLangString() {
        return toParaLangString("\n");
    }

    @Override
    public String toParaLangString(String separator) {
        StringBuilder sb = new StringBuilder();
        for (Entry<String, XDomainString> trEntry : this) {
            String lang = trEntry.getKey();
            String text = trEntry.getValue().value;
            if (text == null)
                continue;
            if (sb.length() != 0)
                sb.append(separator);
            if (lang != null)
                sb.append("<p lang=\"" + lang + "\">");
            sb.append(text);
        }
        return sb.toString();
    }

    @Override
    public String toMultiLangString() {
        return toMultiLangString("\n", null);
    }

    @Override
    public String toMultiLangString(String langSeparator, String lineSeparator) {
        MultiLangStringFormatter formatter = new MultiLangStringFormatter();
        formatter.setDomainSeparator(langSeparator);
        formatter.setLineSeparator(lineSeparator);
        return formatter.format(this);
    }

    /**
     * ParaLang is a lang-mixed text where translations are separated by
     * <code>&lt;p lang='xxx'&gt;</code>.
     */
    static final Pattern paraLangPattern;
    static final int LANG_GROUP = 2;
    static {
        paraLangPattern = Pattern.compile("<p\\s+lang=([\'\"])(.*?)\\1.*?>");
    }

    /**
     * A para-lang formatted domain-string is as:
     * 
     * <pre>
     * ...
     * &lt;p lang="LANG1"&gt;...
     * &lt;p lang="LANG2"&gt;...
     * </pre>
     * 
     * Leading/trailing space of each language is stripped.
     * 
     * @param plText
     *            para-lang string to be parsed.
     * @return <code>null</code> iif <code>plText</code> is <code>null</code>.
     */
    public static XDomainString parseParaLang(String plText) {
        if (plText == null)
            return null;
        Matcher matcher = paraLangPattern.matcher(plText);
        if (!matcher.find())
            return new XDomainString(null, plText);

        String mainText = plText.substring(0, matcher.start());
        XDomainString main = new XDomainString(null, mainText.trim());
        String nextLang;
        int nextStart;
        while (true) {
            nextLang = matcher.group(LANG_GROUP);
            nextStart = matcher.end();

            String part;
            boolean end = false;
            if (matcher.find()) {
                part = plText.substring(nextStart, matcher.start());
            } else {
                part = plText.substring(nextStart);
                end = true;
            }

            part = part.trim();
            main.create(nextLang, part);
            // System.out.println(nextLang + " -> [" + part + "]");
            if (end)
                break;
        }
        return main;
    }

    /**
     * A multi-lang string is formatted as:
     * 
     * <pre>
     * "default-locale"
     * LOCALE1 "string for locale1"
     *         "more..."
     * LOCALE2 "string for locale2"
     *         "more..."
     * </pre>
     * 
     * @param mlstr
     *            multi-lang string to be parsed.
     * @return <code>null</code> iif <code>mlstr</code> is <code>null</code>.
     */
    public static DomainString parseMultiLang(String mlstr) {
        if (mlstr == null)
            return null;
        MultiLangStringParser parser = new MultiLangStringParser();
        return parser.parse(mlstr);
    }

    public static XDomainString of(String plainString) {
        return new XDomainString(plainString);
    }

}
