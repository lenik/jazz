package net.bodz.swt.gui.pfl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternRewrite extends RewriteRule {

    private final Pattern pattern;
    private final String  replacement;

    public PatternRewrite(int flags, Pattern pattern, String replacement) {
        super(flags);
        if (pattern == null)
            throw new NullPointerException("pattern");
        if (replacement == null)
            throw new NullPointerException("replacement");
        if (isIgnoreCase() || isWhole()) {
            String regex = pattern.pattern();
            int _flags = pattern.flags();
            if (isIgnoreCase())
                _flags |= Pattern.CASE_INSENSITIVE;
            if (isWhole())
                regex = "^" + regex + "$";
            pattern = Pattern.compile(regex, _flags);
        }
        this.pattern = pattern;
        this.replacement = replacement;
    }

    @Override
    public boolean matches(String text) {
        Matcher matcher = pattern.matcher(text);
        return isWhole() ? matcher.matches() : matcher.find();
    }

    @Override
    public String rewrite(String text) {
        Matcher m = pattern.matcher(text);
        String dst = isReplaceAll() ? m.replaceAll(replacement) : m.replaceFirst(replacement);
        return dst;
    }

    @Override
    public String toString() {
        StringBuffer buf = new StringBuffer();
        buf.append("(" + pattern + "->" + replacement + ")");
        if (isReplaceAll())
            buf.append('g');
        buf.append("<" + Integer.toBinaryString(flags) + ">");
        return buf.toString();
    }

}
