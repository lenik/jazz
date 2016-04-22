package net.bodz.bas.c.java.text;

import java.text.Collator;
import java.util.Locale;

public class Collators {

    static Collator defaultCollator = Collator.getInstance(Locale.CHINESE);

    public static int compare(Collator collator, String s1, String s2) {
        if (s1 == s2)
            return 0;
        if (s1 == null)
            return -1;
        if (s2 == null)
            return 1;

        if (collator == null)
            collator = defaultCollator;
        return collator.compare(s1, s2);
    }

}
