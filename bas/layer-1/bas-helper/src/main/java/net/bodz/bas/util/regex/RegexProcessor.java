package net.bodz.bas.util.regex;

import java.nio.CharBuffer;
import java.util.regex.Pattern;

import net.bodz.bas.err.NotImplementedException;

public class RegexProcessor
        extends Unescape {

    public RegexProcessor() {
        super("\\");
    }

    @Override
    protected String decode(CharBuffer in) {
        char c = in.get();
        switch (c) {
        case 's':
            return matchSpace();
        case 'S':
            return matchNonspace();
        case 'w':
            return matchWord();
        case 'W':
            return matchNonword();
        }
        // in.position(in.position() - 1);
        // return super.decode(in);
        return "\\" + String.valueOf(c);
    }

    protected String matchSpace() {
        return "\\s";
    }

    protected String matchNonspace() {
        return "\\S";
    }

    protected String matchWord() {
        return "\\w";
    }

    protected String matchNonword() {
        return "\\W";
    }

    @Override
    protected String unmatched(String s) {
        int len = s.length();
        StringBuilder buf = new StringBuilder();
        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            switch (c) {
            case '.':
                buf.append(matchDot());
                break;
            case '^':
                buf.append(matchCaret());
                break;
            case '$':
                buf.append(matchDollar());
                break;
            default:
                buf.append(c);
            }
        }
        return buf.toString();
    }

    protected String matchDot() {
        return ".";
    }

    protected String matchCaret() {
        return "^";
    }

    protected String matchDollar() {
        return "$";
    }

    public Pattern compile(String regex, int flags) {
        String newRegex = super.process(regex);
        return Pattern.compile(newRegex, flags);
    }

    public Pattern compile(String regex) {
        return compile(regex, 0);
    }

    public static class SpaceOverride
            extends RegexProcessor {
        private String spaceRegex;
        private String dotRegex;

        public SpaceOverride(String spaceRegex) {
            this.spaceRegex = "(?:" + spaceRegex + ")";
            // this.dotRegex = "(?:(?!" + spaceRegex + ").)";
            this.dotRegex = "(?:" + spaceRegex + "|.)";
        }

        @Override
        protected String matchSpace() {
            return spaceRegex;
        }

        @Override
        protected String matchNonspace() {
            throw new NotImplementedException();
        }

        @Override
        protected String matchDot() {
            return dotRegex;
        }
    }

    public static final SpaceOverride javaComments;
    public static final SpaceOverride unixComments;
    static {
        javaComments = new SpaceOverride("\\s|//.*?\n|/\\*.*?\\*/");
        unixComments = new SpaceOverride("\\s|#.*?\n");
    }

}
