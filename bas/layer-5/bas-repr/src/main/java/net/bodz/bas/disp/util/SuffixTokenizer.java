package net.bodz.bas.disp.util;

import net.bodz.bas.util.iter.PrefetchedIterator;

/**
 * Example: The suffixes in <code>filename.foo*bar~cat-dog.spec+1.xml:ex</code> are: (The order is
 * important)
 * <ol>
 * <li>.foo
 * <li>*bar
 * <li>~cat-dog
 * <li>.spec+1
 * <li>.xml
 * <li>:ex
 * </ol>
 */
public class SuffixTokenizer
        extends PrefetchedIterator<String> {

    private final CharSequence string;
    private final int length;
    private int pos = -1;
    private boolean started;

    public SuffixTokenizer(CharSequence string) {
        this(string, false);
    }

    public SuffixTokenizer(CharSequence string, boolean includeStartWord) {
        if (string == null)
            throw new NullPointerException("string");
        this.string = string;
        this.length = string.length();
        started = includeStartWord;
    }

    @Override
    protected String fetch() {
        if (pos >= length)
            return end();

        int end = pos + 1;

        L: while (end < length) {
            char c = string.charAt(end);
            switch (c) {
            case '*':
            case '~':
            case ':':
            case '.':
                break L;
            }
            end++;
        }

        String token = string.subSequence(pos == -1 ? 0 : pos, end).toString();
        pos = end;

        if (started)
            return token;

        started = true;
        return fetch();
    }

}
