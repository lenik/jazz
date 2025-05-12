package net.bodz.bas.c.java.util;

import java.util.regex.Pattern;

public class PatternSubst {

    Pattern pattern;
    String replacement;

    public PatternSubst(Pattern pattern, String replacement) {
        this.pattern = pattern;
        this.replacement = replacement;
    }

    public Pattern getPattern() {
        return pattern;
    }

    public void setPattern(Pattern pattern) {
        this.pattern = pattern;
    }

    public String getReplacement() {
        return replacement;
    }

    public void setReplacement(String replacement) {
        this.replacement = replacement;
    }

}
