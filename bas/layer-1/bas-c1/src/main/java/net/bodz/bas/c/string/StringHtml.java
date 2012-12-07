package net.bodz.bas.c.string;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Pattern;

import net.bodz.bas.err.IllegalUsageException;
import net.bodz.bas.err.NotImplementedException;

public class StringHtml {

    private static final String[] entTab0 = new String[1000];
    private static final String[] entTab8000 = new String[2000]; // [8000, 10000)
    private static final Map<String, Character> entRevMap = new HashMap<String, Character>(252);

    static {
        load();
    }

    static void load() {
        String fileName = StringHtml.class.getSimpleName() + ".ent";
        InputStream in = StringHtml.class.getResourceAsStream(fileName);
        if (in == null)
            throw new IllegalUsageException("No resource for " + fileName);

        Properties entMap = new Properties();
        try {
            entMap.load(in);
        } catch (Exception e) {
            throw new IllegalUsageException("Failed to load entities tab", e);
        } finally {
            try {
                in.close();
            } catch (IOException e) {
            }
        }

        for (Object key : entMap.keySet()) {
            String _code = (String) key;
            char code = (char) Integer.parseInt(_code);
            String alias = entMap.getProperty(_code);

            String entity = '&' + alias + ';';
            if (code < 1000)
                entTab0[code] = entity;
            else
                entTab8000[code - 8000] = entity;

            entRevMap.put(alias, code);
        }
    }

    static final Pattern BR = Pattern.compile("<br\\s*/?>", Pattern.MULTILINE);
    static final Pattern P = Pattern.compile("<p\\b.*?>", Pattern.MULTILINE);
    static final Pattern TAG_OPEN = Pattern.compile("<\\w+.*?>", Pattern.MULTILINE);
    static final Pattern TAG_CLOSE = Pattern.compile("</\\w+>");

    public static String toPlain(String html) {
        String s = html;
        s = BR.matcher(s).replaceAll("\n");
        s = P.matcher(s).replaceAll("\n\n");
        s = TAG_OPEN.matcher(s).replaceAll("");
        s = TAG_CLOSE.matcher(s).replaceAll("");
        return s;
    }

    public static String escape(char code) {
        if (code < 1000)
            return entTab0[code];
        else if (code >= 8000 && code < 10000)
            return entTab8000[code - 8000];
        else
            return null;
    }

    /**
     * Escape HTML entities.
     * 
     * @param input
     *            Normal string, maybe <code>null</code>.
     * @return Escaped string, <code>null</code> if input is <code>null</code>.
     * @see http://www.w3.org/TR/html4/sgml/entities.html
     */
    public static String escape(String input) {
        if (input == null)
            return null;

        StringBuilder sb = new StringBuilder(input.length() * 2);
        for (int i = 0; i < input.length(); i++) {
            char code = input.charAt(i);
            String entity = escape(code);
            if (entity != null)
                sb.append(entity);
            else
                sb.append(code);
        }
        return sb.toString();
    }

    public static String escapeDecimal(String input) {
        if (input == null)
            return null;
        StringBuilder sb = new StringBuilder(input.length() * 2);
        for (int i = 0; i < input.length(); i++) {
            char code = input.charAt(i);
            String entity = escape(code);
            if (entity != null) {
                sb.append("&#");
                sb.append((int) code);
                sb.append(';');
            } else {
                sb.append(code);
            }
        }
        return sb.toString();
    }

    public static String escapeHex(String input) {
        if (input == null)
            return null;
        StringBuilder sb = new StringBuilder(input.length() * 2);
        for (int i = 0; i < input.length(); i++) {
            char code = input.charAt(i);
            String entity = escape(code);
            if (entity != null) {
                sb.append("&#x");
                sb.append(Integer.toString((int) code, 16));
                sb.append(';');
            } else {
                sb.append(code);
            }
        }
        return sb.toString();
    }

    public static String unescape(String input) {
        if (input == null)
            return null;
        throw new NotImplementedException();
    }

}
