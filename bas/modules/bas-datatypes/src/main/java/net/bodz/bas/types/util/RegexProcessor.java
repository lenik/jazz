package net.bodz.bas.types.util;

import java.nio.CharBuffer;
import java.util.regex.Pattern;

import net.bodz.bas.lang.err.NotImplementedException;
import net.bodz.bas.text.util.Unescape;

public class RegexProcessor extends Unescape {

    public RegexProcessor() {
        super("\\"); //$NON-NLS-1$
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
        return "\\" + String.valueOf(c); //$NON-NLS-1$
    }

    protected String matchSpace() {
        return "\\s"; //$NON-NLS-1$
    }

    protected String matchNonspace() {
        return "\\S"; //$NON-NLS-1$
    }

    protected String matchWord() {
        return "\\w"; //$NON-NLS-1$
    }

    protected String matchNonword() {
        return "\\W"; //$NON-NLS-1$
    }

    @Override
    protected String unmatched(String s) {
        int len = s.length();
        StringBuffer buf = new StringBuffer();
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
        return "."; //$NON-NLS-1$
    }

    protected String matchCaret() {
        return "^"; //$NON-NLS-1$
    }

    protected String matchDollar() {
        return "$"; //$NON-NLS-1$
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
            this.spaceRegex = "(?:" + spaceRegex + ")"; //$NON-NLS-1$ //$NON-NLS-2$
            // this.dotRegex = "(?:(?!" + spaceRegex + ").)";
            this.dotRegex = "(?:" + spaceRegex + "|.)"; //$NON-NLS-1$ //$NON-NLS-2$
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
        javaComments = new SpaceOverride("\\s|//.*?\n|/\\*.*?\\*/"); //$NON-NLS-1$
        unixComments = new SpaceOverride("\\s|#.*?\n"); //$NON-NLS-1$
    }

}
