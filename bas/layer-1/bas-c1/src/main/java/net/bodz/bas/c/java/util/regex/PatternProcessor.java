package net.bodz.bas.c.java.util.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.bodz.bas.meta.util.OverrideOption;
import net.bodz.bas.proxy.java.lang.StringBuilderDecorator;

public class PatternProcessor
        extends StringBuilderDecorator {

    protected Pattern pattern;
    protected String source;

    protected Matcher matcher;
    protected int matchIndex;

    /** end() position of last match */
    protected int lastPosition;

    protected int count;

    public PatternProcessor(Pattern pattern) {
        super(null);
        this.pattern = pattern;
    }

    public PatternProcessor(String regex) {
        this(Pattern.compile(regex));
    }

    public PatternProcessor(String regex, int flags) {
        this(Pattern.compile(regex, flags));
    }

    protected void matched(int start, int end) {
        matched(source.substring(start, end));
    }

    @OverrideOption
    protected void matched(String part) {
        append(part);
    }

    protected void unmatched(int start, int end) {
        unmatched(source.substring(start, end));
    }

    @OverrideOption
    protected void unmatched(String part) {
        append(part);
    }

    public synchronized String process(String source) {
        this.source = source;
        matcher = pattern.matcher(source);
        matchIndex = 0;
        lastPosition = 0;
        count = 0;
        impl = new StringBuilder();
        while (matcher.find()) {
            int start = matcher.start();
            int end = matcher.end();

            // Head (groups)
            if (start > lastPosition)
                unmatched(lastPosition, start);

            // Body (groups)
            // if (end > start)
            matched(start, end);

            lastPosition = end;
            matchIndex++;

            count++;
        }

        // Foot (suffix)
        if (lastPosition < source.length())
            unmatched(lastPosition, source.length());

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
