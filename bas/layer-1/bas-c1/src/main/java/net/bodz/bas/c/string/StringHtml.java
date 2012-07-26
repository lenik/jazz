package net.bodz.bas.c.string;

import java.util.regex.Pattern;

public class StringHtml {

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

}
