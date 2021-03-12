package net.bodz.bas.c.java.util.regex;

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

    protected abstract String matched(String part);

    protected abstract String unmatched(String part);

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
                String part = source.substring(lastPosition, start);
                part = unmatched(part);
                append(part);
            }

            // Body (groups)
            // if (end > start)
            String part = source.substring(start, end);
            part = matched(part);
            append(part);

            lastPosition = end;
            matchIndex++;

            count++;
        }

        // Foot (suffix)
        if (lastPosition < source.length()) {
            String part = source.substring(lastPosition, source.length());
            part = unmatched(part);
            append(part);
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

    public static abstract class Matches
            extends TextPrepByParts {

        public Matches(Pattern pattern) {
            super(pattern);
        }

        public Matches(String pattern) {
            super(pattern);
        }

        @Override
        protected String unmatched(String part) {
            return part;
        }

    }

    public static abstract class Unmatches
            extends TextPrepByParts {

        public Unmatches(Pattern pattern) {
            super(pattern);
        }

        public Unmatches(String pattern) {
            super(pattern);
        }

        @Override
        protected String matched(String part) {
            return part;
        }

    }

    public static TextPrepByParts match(String pattern, IPartProcessor proc) {
        return match(Pattern.compile(pattern), proc);
    }

    public static TextPrepByParts match(Pattern pattern, final IPartProcessor proc) {
        return new Matches(pattern) {
            @Override
            protected String matched(String part) {
                return proc.process(part, matcher);
            }
        };
    }

    public static TextPrepByParts unmatch(String pattern, IPartProcessor proc) {
        return unmatch(Pattern.compile(pattern), proc);
    }

    public static TextPrepByParts unmatch(Pattern pattern, final IPartProcessor proc) {
        return new Unmatches(pattern) {
            @Override
            protected String unmatched(String part) {
                return proc.process(part, matcher);
            }
        };
    }

}
