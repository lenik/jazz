package net.bodz.bas.repr.request;

import net.bodz.bas.util.iter.PrefetchedIterator;

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
            case '.':
            case ':':
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
