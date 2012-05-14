package net.bodz.bas.disp.util;


public class MethodPattern {

    private final Class<?>[] pattern;

    public MethodPattern(Class<?>... pattern) {
        if (pattern == null)
            throw new NullPointerException("pattern");
        this.pattern = pattern;
    }

}
