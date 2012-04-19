package net.bodz.bas.c.java.util.regex;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GlobPattern
        implements Serializable {

    private static final long serialVersionUID = 1L;

    private final Pattern pattern;

    private GlobPattern(Pattern pattern) {
        this.pattern = pattern;
    }

    /**
     * @see java.util.regex.Pattern#pattern()
     */
    public String pattern() {
        return pattern.pattern();
    }

    /**
     * @see java.util.regex.Pattern#matcher(java.lang.CharSequence)
     */
    public Matcher matcher(CharSequence input) {
        return pattern.matcher(input);
    }

    /**
     * @see java.util.regex.Pattern#flags()
     */
    public int flags() {
        return pattern.flags();
    }

    /**
     * @see java.util.regex.Pattern#split(java.lang.CharSequence, int)
     */
    public String[] split(CharSequence input, int limit) {
        return pattern.split(input, limit);
    }

    /**
     * @see java.util.regex.Pattern#split(java.lang.CharSequence)
     */
    public String[] split(CharSequence input) {
        return pattern.split(input);
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        int hash = 0x5987aab8;
        hash ^= pattern.hashCode();
        return hash;
    }

    /**
     * @see java.util.regex.Pattern#toString()
     */
    public String toString() {
        return pattern.toString();
    }

    public static String regexFromGlob(String glob) {
        String regex = Pattern.quote(glob);
        regex = regex.replace("*", "\\E.*\\Q");
        regex = regex.replace("?", "\\E.\\Q");
        return regex;
    }

    public static GlobPattern compile(String glob) {
        return compile(glob, 0);
    }

    public static GlobPattern compile(String glob, int flags) {
        String regex = regexFromGlob(glob);
        Pattern pattern = Pattern.compile(regex, flags);
        return new GlobPattern(pattern);
    }

    public static boolean matches(String glob, CharSequence input) {
        String regex = regexFromGlob(glob);
        return Pattern.matches(regex, input);
    }

}
