package net.bodz.bas.regex;

import java.util.regex.Pattern;

public class Patterns {

    public static Pattern fromWildcards(String wildcards, boolean caseInsensitive) {
        if (wildcards == null)
            throw new NullPointerException("null wildcards");
        int flags = 0;
        if (caseInsensitive)
            flags |= Pattern.CASE_INSENSITIVE;
        String regex = wildcards;
        regex = regex.replace(".", "\\.");
        regex = regex.replace("*", ".*");
        regex = regex.replace("?", ".");
        regex = regex.replaceAll("([{}()\\[\\]+])", "\\$1");
        return Pattern.compile(regex, flags);
    }

}
