package net.bodz.bas.text.diff;

import net.bodz.bas.text.diff.gnudiff.GNUDiffComparator;

public class DiffComparators {

    public static final DiffComparator gnudiff;

    static {
        gnudiff = new GNUDiffComparator();
    }

}
