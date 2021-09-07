package net.bodz.bas.c.java.util.regex;

import java.io.IOException;
import java.util.regex.Pattern;

public class TextPreps {

    public static abstract class Matches
            extends TextPrepByParts {

        public Matches(Pattern pattern) {
            super(pattern);
        }

        public Matches(String pattern) {
            super(pattern);
        }

        @Override
        protected void unmatched(CharSequence in, int start, int end, Appendable out)
                throws IOException {
            out.append(in, start, end);
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
        protected void matched(CharSequence in, int start, int end, Appendable out)
                throws IOException {
            out.append(in, start, end);
        }

    }

    public static TextPrepByParts match(String pattern, IPartProcessor proc) {
        return match(Pattern.compile(pattern), proc);
    }

    public static TextPrepByParts match(Pattern pattern, final IPartProcessor proc) {
        return new Matches(pattern) {
            @Override
            protected void matched(CharSequence in, int start, int end, Appendable out)
                    throws IOException {
                proc.process(in, start, end, out, matcher);
            }
        };
    }

    public static TextPrepByParts unmatch(String pattern, IPartProcessor proc) {
        return unmatch(Pattern.compile(pattern), proc);
    }

    public static TextPrepByParts unmatch(Pattern pattern, final IPartProcessor proc) {
        return new Unmatches(pattern) {
            @Override
            protected void unmatched(CharSequence in, int start, int end, Appendable out)
                    throws IOException {
                proc.process(in, start, end, out, matcher);
            }
        };
    }

}
