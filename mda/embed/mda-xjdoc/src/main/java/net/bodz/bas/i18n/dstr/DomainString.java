package net.bodz.bas.i18n.dstr;

import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DomainString
        extends DomainNode<DomainString, String> {

    public DomainString(String value) {
        super(null, value);
    }

    public DomainString(String domain, String value) {
        super(domain, value);
    }

    public DomainString(String domain, String value, DomainString... follows) {
        super(domain, value, follows);
    }

    @Override
    protected DomainString create(String domain, String value) {
        return new DomainString(domain, value);
    }

    @Override
    public String toString() {
        return value;
    }

    public String toParaLangString() {
        return toParaLangString("\n");
    }

    public String toParaLangString(String separator) {
        StringBuilder sb = new StringBuilder();
        for (Entry<String, DomainString> trEntry : dump()) {
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

    public String toMultiLangString() {
        return toMultiLangString("\n", null);
    }

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
     */
    public static DomainString parseParaLang(String plText) {
        Matcher matcher = paraLangPattern.matcher(plText);
        if (!matcher.find())
            return new DomainString(null, plText);

        String mainText = plText.substring(0, matcher.start());
        DomainString main = new DomainString(null, mainText.trim());
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
            main.resolve(nextLang, part);
            // System.out.println(nextLang + " -> [" + part + "]");
            if (end)
                break;
        }
        return main;
    }

    public static DomainString parseMultiLangString(String multiLangString) {
        MultiLangStringParser parser = new MultiLangStringParser();
        return parser.parse(multiLangString);
    }

}
