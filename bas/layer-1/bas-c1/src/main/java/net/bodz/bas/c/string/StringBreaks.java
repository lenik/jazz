package net.bodz.bas.c.string;

import java.util.Iterator;

import net.bodz.bas.t.iterator.PrefetchSeed;

public class StringBreaks
        implements Iterable<String> {

    private final String s;
    private final char delim;
    private final boolean includeDelim;

    public StringBreaks(String s) {
        this(s, '\n', false);
    }

    public StringBreaks(String s, char delim, boolean includeDelim) {
        if (s == null)
            throw new NullPointerException("s");
        this.s = s;
        this.delim = delim;
        this.includeDelim = includeDelim;
    }

    @Override
    public Iterator<String> iterator() {
        return new Breaker(s, delim, includeDelim);
    }

    static class Breaker
            extends PrefetchSeed<String> {

        private String s;
        private char delim;
        private boolean includeDelim;
        private int start = 0;

        public Breaker(String s) {
            this(s, '\n', false);
        }

        public Breaker(String s, char delim, boolean includeDelim) {
            this.s = s;
            this.delim = delim;
            this.includeDelim = includeDelim;
        }

        @Override
        public Breaker clone() {
            Breaker o = new Breaker(s, delim, includeDelim);
            o.init(this);
            o.start = start;
            return o;
        }

        @Override
        protected String fetch() {
            if (start == -1)
                return end();

            int next = s.indexOf(delim, start);

            String item;
            if (next == -1)
                item = s.substring(start);
            else if (includeDelim)
                item = s.substring(start, next + 1);
            else
                item = s.substring(start, next);

            if (next == -1)
                start = -1;
            else
                start = next + 1;

            return item;
        }

    }

}
