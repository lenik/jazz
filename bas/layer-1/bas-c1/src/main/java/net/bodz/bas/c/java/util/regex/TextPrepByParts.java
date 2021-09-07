package net.bodz.bas.c.java.util.regex;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.bodz.bas.proxy.java.lang.DecoratedStringBuilder;

public abstract class TextPrepByParts
        extends DecoratedStringBuilder {

    protected Pattern pattern;
    protected String source;

    protected Matcher matcher;
    protected int matchIndex;

    /** end() position of last match */
    protected int lastPosition;

    protected int count;

    protected TextPrepByParts(Pattern pattern) {
        super(null);
        this.pattern = pattern;
    }

    protected TextPrepByParts(String pattern) {
        this(Pattern.compile(pattern));
    }

    public Matcher getMatcher() {
        return matcher;
    }

    protected abstract void matched(CharSequence in, int start, int end, Appendable out)
            throws IOException;

    protected abstract void unmatched(CharSequence in, int start, int end, Appendable out)
            throws IOException;

    public synchronized String process(String source) {
        this.source = source;
        matcher = pattern.matcher(source);
        matchIndex = 0;
        lastPosition = 0;
        count = 0;
        setWrapped(new StringBuilder());
        while (matcher.find()) {
            int start = matcher.start();
            int end = matcher.end();

            // Head (groups)
            if (start > lastPosition) {
                try {
                    unmatched(source, lastPosition, start, this);
                } catch (IOException e) {
                    throw new RuntimeException(e.getMessage(), e);
                }
            }

            // Body (groups)
            // if (end > start)
            try {
                matched(source, start, end, this);
            } catch (IOException e) {
                throw new RuntimeException(e.getMessage(), e);
            }

            lastPosition = end;
            matchIndex++;

            count++;
        }

        // Foot (suffix)
        int n = source.length();
        if (lastPosition < n) {
            try {
                unmatched(source, lastPosition, n, this);
            } catch (IOException e) {
                throw new RuntimeException(e.getMessage(), e);
            }
        }

        return super.toString();
    }

    public int getMatchedCount() {
        return count;
    }

    @Override
    public String toString() {
        return getClass() + "-" + System.identityHashCode(this);
    }

}
