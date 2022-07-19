package net.bodz.bas.c.string;

import java.util.StringTokenizer;

public class StringId {

    final String delimPattern;
    final char delim;
    final String delimStr;
    TrimMode trimDelim = TrimMode.TRIM;

    public StringId(String delimPattern) {
        this(delimPattern, delimPattern.charAt(0));
    }

    public StringId(String delimPattern, char delim) {
        if (delimPattern == null)
            throw new NullPointerException("delim");
        this.delimPattern = delimPattern;
        this.delim = delim;
        this.delimStr = "" + delim;
    }

    public static String trim(String s, String delim) {
        s = trimLeft(s, delim);
        s = trimRight(s, delim);
        return s;
    }

    public static String trimLeft(String s, String delim) {
        int len = s.length();
        int pos = 0;
        while (pos < len) {
            char ch = s.charAt(pos);
            if (delim.indexOf(ch) == -1)
                return s.substring(pos);
            pos++;
        }
        return s;
    }

    public static String trimRight(String s, String delim) {
        int len = s.length();
        int pos = len - 1;
        while (pos >= 0) {
            char ch = s.charAt(pos);
            if (delim.indexOf(ch) == -1)
                return s.substring(0, pos + 1);
            pos--;
        }
        return s;
    }

    /**
     * helloWorld => hello-world
     */
    public String breakCamel(String id) {
        return breakCamel(id, delimStr);
    }

    public String breakCamel(String id, String tokenSep) {
        switch (trimDelim) {
        case LTRIM:
            id = trimLeft(id, delimPattern);
            break;
        case TRIM:
            id = trimLeft(id, delimPattern);
        case RTRIM:
            id = trimRight(id, delimPattern);
            break;
        default:
        }

        StringBuilder buf = new StringBuilder(id.length() * 2);
        for (int wordStart = 0; wordStart < id.length();) {
            int wordEnd;
            wordEnd = StringSearch.indexOfNoncontinuous(id, Character.UPPERCASE_LETTER, wordStart + 1);
            if (wordEnd == -1)
                wordEnd = id.length();
            String word = id.substring(wordStart, wordEnd);
            word = Strings.lcfirst(word);
            buf.append(tokenSep);
            buf.append(word);
            wordStart = wordEnd;
        }
        String s = buf.toString();
        if (s.startsWith(tokenSep))
            s = s.substring(1);
        return s; // .toLowerCase();
    }

    /**
     * hello-world => helloWorld
     */
    public String toCamel(String hstr) {
        StringBuilder buf = new StringBuilder();
        StringTokenizer tokens = new StringTokenizer(hstr, delimPattern);
        int i = 0;
        while (tokens.hasMoreTokens()) {
            String token = tokens.nextToken();
            if (token.isEmpty())
                buf.append('_');
            else {
                if (i != 0)
                    token = Strings.ucfirst(token);
                buf.append(token);
            }
            i++;
        }
        return buf.toString();
    }

    /** hyphen-id */
    public static final StringId HYPHEN = new StringId("-");

    /** underline_id */
    public static final StringId UL = new StringId("_");

    /** space id */
    public static final StringId SPACE = new StringId(" \t\r\n");

    /** mix-ed id */
    public static final StringId MIXED = new StringId("-_ \t\r\n");

}
