package net.bodz.bas.types.util;

import java.util.regex.Pattern;

import net.bodz.bas.lang.err.NotImplementedException;
import net.bodz.bas.text.interp.Unescape;

public class RegexProcessor extends Unescape {

    public RegexProcessor() {
        super("\\");
    }

    @Override
    protected int recognizeEscapeCode(int start) {
        char c = source.charAt(start);
        switch (c) {
        case 's':
            matchSpace();
            return 1;
        case 'S':
            matchNonspace();
            return 1;
        case 'w':
            matchWord();
            return 1;
        case 'W':
            matchNonword();
            return 1;
        }
        return -1;
    }

    protected void matchSpace() {
        print("\\s");
    }

    protected void matchNonspace() {
        print("\\S");
    }

    protected void matchWord() {
        print("\\w");
    }

    protected void matchNonword() {
        print("\\W");
    }

    @Override
    protected void unmatched(int start, int end) {
        assert start < end; // len > 0
        for (int i = start; i < end; i++) {
            char c = source.charAt(i);
            switch (c) {
            case '.':
                matchDot();
                break;
            case '^':
                matchCaret();
                break;
            case '$':
                matchDollar();
                break;
            default:
                print(c);
            }
        }
    }

    protected void matchDot() {
        print('.');
    }

    protected void matchCaret() {
        print('^');
    }

    protected void matchDollar() {
        print('$');
    }

    public Pattern compile(String regex, int flags) {
        String newRegex = super.process(regex);
        return Pattern.compile(newRegex, flags);
    }

    public Pattern compile(String regex) {
        return compile(regex, 0);
    }

    public static class SpaceOverride extends RegexProcessor {
        private String spaceRegex;
        private String dotRegex;

        public SpaceOverride(String spaceRegex) {
            this.spaceRegex = "(?:" + spaceRegex + ")";
            // this.dotRegex = "(?:(?!" + spaceRegex + ").)";
            this.dotRegex = "(?:" + spaceRegex + "|.)";
        }

        @Override
        protected void matchSpace() {
            print(spaceRegex);
        }

        @Override
        protected void matchNonspace() {
            throw new NotImplementedException();
        }

        @Override
        protected void matchDot() {
            print(dotRegex);
        }
    }

    public static final SpaceOverride javaComments;
    public static final SpaceOverride unixComments;
    static {
        javaComments = new SpaceOverride("\\s|//.*?\n|/\\*.*?\\*/");
        unixComments = new SpaceOverride("\\s|#.*?\n");
    }

}
