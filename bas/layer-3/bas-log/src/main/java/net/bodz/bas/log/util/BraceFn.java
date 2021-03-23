package net.bodz.bas.log.util;

public class BraceFn {

    /**
     * A text region is marked by a pair of strings, the first for the region start, and the other
     * for the end of the region.
     *
     * <p>
     * This function is used to generate the end marker corresponding to the given start marker.
     *
     * <p>
     * For example: A region started by <code>[region(</code> should be ended by
     * <code>region]</code>.
     */
    public static String getClosingStr(String openingStr) {
        StringBuilder buf = new StringBuilder();
        int lastWordStart = -1;
        int n = openingStr.length();
        for (int i = 0; i <= n; i++) {
            char cp = 0;
            if (i < n) {
                char ch = 0;
                ch = openingStr.charAt(i);
                switch (ch) {
                case '(':
                    cp = ')';
                    break;
                case '[':
                    cp = ']';
                    break;
                case '{':
                    cp = '}';
                    break;
                case '<':
                    cp = '>';
                    break;
                default:
                    if (lastWordStart == -1)
                        lastWordStart = i;
                    continue;
                }
            }
            if (lastWordStart != -1) {
                for (int j = i - 1; j >= lastWordStart; j--) {
                    char y = openingStr.charAt(j);
                    buf.append(y);
                }
                lastWordStart = -1;
            }
            if (cp != 0)
                buf.append(cp);
        }
        buf.reverse();
        String result = buf.toString();
        return result;
    }

}
