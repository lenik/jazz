package net.bodz.bas.text.util;

import java.util.regex.Pattern;

public class Patterns {

    public static Pattern fromWildcards(String wildcards, boolean caseInsensitive) {
        if (wildcards == null)
            throw new NullPointerException("null wildcards"); //$NON-NLS-1$
        int flags = 0;
        if (caseInsensitive)
            flags |= Pattern.CASE_INSENSITIVE;
        String regex = wildcards;
        regex = regex.replace(".", "\\."); //$NON-NLS-1$ //$NON-NLS-2$
        regex = regex.replace("*", ".*"); //$NON-NLS-1$ //$NON-NLS-2$
        regex = regex.replace("?", "."); //$NON-NLS-1$ //$NON-NLS-2$
        regex = regex.replaceAll("([{}()\\[\\]+])", "\\$1"); //$NON-NLS-1$ //$NON-NLS-2$
        return Pattern.compile(regex, flags);
    }

}
