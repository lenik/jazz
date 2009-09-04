package net.bodz.swt.gui.pfl;


public class TextRewrite extends RewriteRule {

    private final String pattern;
    private final String replacement;

    public TextRewrite(String pattern, String replacement) {
        this(0, pattern, replacement);
    }

    public TextRewrite(int flags, String pattern, String replacement) {
        super(flags);
        if (pattern == null)
            throw new NullPointerException("pattern");
        if (pattern.isEmpty())
            throw new IllegalArgumentException("pattern is empty");
        if (replacement == null)
            throw new NullPointerException("replacement");
        if (isIgnoreCase())
            pattern = pattern.toLowerCase();
        this.pattern = pattern;
        this.replacement = replacement;
    }

    @Override
    public int getFlags() {
        return flags;
    }

    @Override
    public boolean matches(String text) {
        if (isIgnoreCase())
            text = text.toLowerCase();
        if (isWhole())
            return text.equals(pattern);
        else
            return text.indexOf(pattern) != -1;
    }

    @Override
    public String rewrite(String text) {
        if (text == null)
            return null;
        String _text = text;
        if (isIgnoreCase())
            _text = text.toLowerCase();

        if (isWhole())
            return _text.equals(pattern) ? replacement : text;

        int q = 1;
        if (isReplaceAll()) {
            if (!isIgnoreCase())
                return text.replace(pattern, replacement);
            q = Integer.MAX_VALUE;
        }

        int start = 0;
        while (q == Integer.MAX_VALUE || q-- > 0) {
            start = _text.indexOf(pattern, start);
            if (start == -1)
                break;
            text = text.substring(0, start) + replacement
                    + text.substring(start + pattern.length());
            start += replacement.length();
        }
        return text;
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
