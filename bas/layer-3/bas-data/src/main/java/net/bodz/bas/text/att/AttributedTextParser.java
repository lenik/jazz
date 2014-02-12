package net.bodz.bas.text.att;

import java.util.StringTokenizer;

import net.bodz.bas.err.ParseException;

public class AttributedTextParser {

    public static AttributedText parse(String src)
            throws ParseException {
        return parse(new AttributedText(), src);
    }

    public static AttributedText parse(AttributedText att, String src)
            throws ParseException {
        if (att == null)
            throw new NullPointerException("att");
        if (src == null)
            throw new NullPointerException("src");

        String headers;
        String body;

        int sep = src.indexOf("\n\n");
        if (sep == -1) {
            headers = src;
            body = null;
        } else {
            headers = src.substring(0, sep);
            body = src.substring(sep + 2).trim();
        }
        att.setText(body);

        StringTokenizer tokens = new StringTokenizer(headers, "\n", false);
        while (tokens.hasMoreTokens()) {
            String header = tokens.nextToken().trim();
            if (header.isEmpty())
                continue;

            String key;
            int eq = header.indexOf('=');
            if (eq == -1) {
                key = header;
                header = null;
                // att.remove(key);
            } else {
                key = header.substring(0, eq).trim();
                header = header.substring(eq + 1).trim();
                att.setAttribute(key, header);
            }
        }
        return att;
    }

}
