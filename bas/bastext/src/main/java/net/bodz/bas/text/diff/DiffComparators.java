package net.bodz.bas.text.diff;

import net.bodz.extern.diff.GNUDiff;

public class DiffComparators {

    public static final DiffComparator gnudiff;

    static {
        gnudiff = new GNUDiff();
    }

}
