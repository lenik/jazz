package net.bodz.bas.c.string;

public class StringStat {

    public static int count(String s, char literalPattern) {
        int count = 0;
        int index = 0;
        while ((index = s.indexOf(literalPattern, index)) != -1) {
            index++;
            count++;
        }
        return count;
    }

    public static int count(String s, String literalPattern) {
        int count = 0;
        int index = 0;
        while ((index = s.indexOf(literalPattern, index)) != -1) {
            index += literalPattern.length();
            count++;
        }
        return count;
    }

    public static int count(String s, byte charCategory) {
        int count = 0;
        int index = 0;
        while ((index = StringSearch.indexOf(s, charCategory, index)) != -1) {
            index++;
            count++;
        }
        return count;
    }

    public static int wordCount(String data) {
        int count = 0;
        int pos = 0;
        int len = data.length();
        boolean started = false;
        while (pos < len) {
            char ch = data.charAt(pos++);
            boolean space = Character.isWhitespace(ch);
            if (space && started)
                count++;
            started = !space;
        }
        if (started)
            count++;
        return count;
    }

}
