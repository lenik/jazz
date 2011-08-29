package net.bodz.bas.util.string;


public class StringFeature {

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

}
