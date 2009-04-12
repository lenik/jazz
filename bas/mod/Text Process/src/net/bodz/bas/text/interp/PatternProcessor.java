package net.bodz.bas.text.interp;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.bodz.bas.io.CharOuts.BCharOut;
import net.bodz.bas.lang.a.OverrideOption;

public class PatternProcessor extends BCharOut {

    protected Pattern pattern;
    protected String  source;

    protected Matcher matcher;
    protected int     matchIndex;

    /** end() position of last match */
    protected int     matchBegin;

    protected int     count;

    public PatternProcessor(Pattern pattern) {
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
        print(part);
    }

    protected void unmatched(int start, int end) {
        unmatched(source.substring(start, end));
    }

    @OverrideOption
    protected void unmatched(String part) {
        print(part);
    }

    public synchronized String process(String source) {
        this.source = source;
        matcher = pattern.matcher(source);
        matchIndex = 0;
        matchBegin = 0;
        count = 0;
        setBuffer(new StringBuffer());
        while (matcher.find()) {
            int start = matcher.start();
            int end = matcher.end();

            // Head (groups)
            if (start > matchBegin)
                unmatched(matchBegin, start);

            // Body (groups)
            // if (end > start)
            matched(start, end);

            matchBegin = end;
            matchIndex++;

            count++;
        }

        // Foot (suffix)
        if (matchBegin < source.length())
            unmatched(matchBegin, source.length());

        return super.toString();
    }

    public int getMatchedCount() {
        return count;
    }

    @Override
    public String toString() {
        return getClass() + "-" + System.identityHashCode(this); //$NON-NLS-1$
    }

}
