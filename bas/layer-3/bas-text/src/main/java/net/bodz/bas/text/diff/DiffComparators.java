package net.bodz.bas.text.diff;

import net.bodz.bas.text.diff.gnudiff.GNUDiffComparator;

public class DiffComparators {

    public static final IDiffComparator gnudiff;

    static {
        gnudiff = new GNUDiffComparator();
    }

}
