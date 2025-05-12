package net.bodz.bas.c.java.util;

import java.util.regex.Pattern;

import net.bodz.bas.meta.decl.NotNull;

public class Patterns {

    public static Pattern compile(@NotNull String regex, @NotNull String options) {
        int flags = 0;
        for (char opt : options.toCharArray()) {
            switch (opt) {
                case 'i':
                    flags |= Pattern.CASE_INSENSITIVE;
                    break;
                case 'x':
                    flags |= Pattern.COMMENTS;
                    break;
                case 's':
                    flags |= Pattern.DOTALL;
                    break;
                case 'm':
                    flags |= Pattern.MULTILINE;
                    break;
                default:
                    throw new IllegalArgumentException("Invalid option char: " + opt);
            }
        }
        return Pattern.compile(regex, flags);
    }

    public static Pattern match(String matchExpr) {
        String s = matchExpr;
        if (s.startsWith("m/"))
            s = s.substring(2);
        else if (s.startsWith("/"))
            s = s.substring(1);
        else
            throw new IllegalArgumentException("match expr should start with m/ or /: " + s);

        int lastSlash = s.lastIndexOf('/');
        if (lastSlash == -1)
            throw new IllegalArgumentException("match expr should end with /: " + s);
        String regex = s.substring(0, lastSlash);
        String options = s.substring(lastSlash + 1);
        return compile(regex, options);
    }

    public static PatternSubst subst(@NotNull String matchExpr, @NotNull String replacement)
            throws Exception {
        Pattern pattern = match(matchExpr);
        return new PatternSubst(pattern, replacement);
    }

    public static PatternSubst subst(@NotNull String substExpr)
            throws Exception {
        String s = substExpr;
        if (!s.startsWith("s/"))
            throw new IllegalArgumentException("subst expr should start with s/");
        s = s.substring(2);

        int slash = indexOfCstr(s, '/');
        if (slash == -1)
            throw new IllegalArgumentException("pattern should end with /: " + s);
        String regex = s.substring(0, slash);
        s = s.substring(slash + 1);

        int lastSlash = s.lastIndexOf('/');
        if (lastSlash == -1)
            throw new IllegalArgumentException("replacement should end with /: " + s);
        String replacement = s.substring(0, lastSlash);
        s = s.substring(slash + 1);

        Pattern pattern = compile(regex, s);
        return new PatternSubst(pattern, replacement);
    }

    static int indexOfCstr(String cStr, char ch) {
        int len = cStr.length();
        boolean escaped = false;
        for (int i = 0; i < len; i++) {
            if (escaped) {
                escaped = false;
                continue;
            }
            char c = cStr.charAt(i);
            if (c == '\\') {
                escaped = true;
                continue;
            }
            if (c == ch) {
                return i;
            }
        }
        return -1;
    }

}