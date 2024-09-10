package net.bodz.bas.c.string;

import net.bodz.bas.t.iterator.PrefetchedIterator;

/**
 * Split by delim sequence (string), like <code>\n\n</code> , <code>***</code>.
 *
 * Allow extra whitespace in the delim sequence, for example, <code>Foo***Bar</code> can be written
 * as <code>Foo*  \n*\t*Bar</code>.
 *
 * However space outside the delim sequence are preserved.
 */
public class DelimSeqTokenizer
        extends PrefetchedIterator<String> {

    private char[] pattern;

    private String str;
    private int len;

    private boolean ended;
    private int nextStart;
    private int m;
    private int end;

    /**
     * Should be optimized by the caller.
     */
    @Deprecated
    public DelimSeqTokenizer(String str, String pattern) {
        this(str, pattern.toCharArray());
    }

    public DelimSeqTokenizer(String str, char... pattern) {
        this.str = str;
        this.len = str.length();
        this.pattern = pattern;
    }

    public int getEnd() {
        return end;
    }

    public int getNextStart() {
        return nextStart;
    }

    public String getRemaining() {
        return str.substring(nextStart);
    }

    @Override
    public String fetch() {
        if (ended)
            return end();

        int start = nextStart;
        for (int i = start; i < len; i++) {
            char ch = str.charAt(i);
            if (ch == pattern[m]) {
                if (m++ == 0)
                    end = i;
                if (m == pattern.length) {
                    nextStart = i + 1;
                    m = 0;
                    return str.substring(start, end);
                }
            } else {
                if (Character.isWhitespace(ch))
                    // only have effects when inside the delim seq.
                    continue;
                m = 0;
            }
        }

        // nextStart = end = len;
        ended = true;
        return str.substring(start);
    }

}
