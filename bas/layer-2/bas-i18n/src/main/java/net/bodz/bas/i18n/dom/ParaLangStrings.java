package net.bodz.bas.i18n.dom;

import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParaLangStrings {

    public static String format(iString ds) {
        return format(ds, "\n");
    }

    public static String format(iString ds, String separator) {
        StringBuilder sb = new StringBuilder();
        for (Entry<String, String> trEntry : ds.entrySet()) {
            String lang = trEntry.getKey();
            String text = trEntry.getValue();
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

    /**
     * ParaLang is a lang-mixed text where translations are separated by
     * <code>&lt;p lang='xxx'&gt;</code>.
     */
    static final Pattern TAG_PATTERN;
    static final int LANG_GROUP = 2;
    static {
        TAG_PATTERN = Pattern.compile("<p\\s+lang=([\'\"])(.*?)\\1.*?>");
    }

    /**
     * A para-lang string is formatted as:
     *
     * <pre>
     * string for default-locale
     *
     * &lt;p lang="LOCALE1"&gt;
     *      string for locale1...
     *
     * &lt;p lang="LOCALE2"&gt;
     *      string for locale2...
     * </pre>
     *
     * @see ParaLangStrings#parse(iString, String)
     */
    public static iString parse(String plText) {
        XiString ds = new XiString();
        ParaLangStrings.parse(ds, plText);
        return ds;
    }

    public static void parse(iString output, String plText) {
        if (plText == null)
            return;

        Matcher matcher = TAG_PATTERN.matcher(plText);
        if (!matcher.find()) {
            output.put(null, plText);
            return;
        }

        String mainText = plText.substring(0, matcher.start());
        output.put(null, mainText.trim());

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
            output.put(nextLang, part);
            // System.out.println(nextLang + " -> [" + part + "]");
            if (end)
                break;
        }
    }

}
