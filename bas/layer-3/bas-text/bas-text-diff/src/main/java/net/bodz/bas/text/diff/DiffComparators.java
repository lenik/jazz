package net.bodz.bas.text.diff;

import net.bodz.bas.text.diff.gnudiff.GNUDiff;

public class DiffComparators {

    public static final DiffComparator gnudiff;

    static {
        gnudiff = new GNUDiff();
    }

}
