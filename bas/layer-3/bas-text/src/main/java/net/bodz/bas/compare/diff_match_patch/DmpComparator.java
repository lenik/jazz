package net.bodz.bas.compare.diff_match_patch;

import java.util.List;

import net.bodz.bas.compare.IListComparator;
import net.bodz.bas.compare.IListCompareResult;

import name.fraser.neil.plaintext.diff_match_patch;

public class DmpComparator
        implements
            IListComparator<String, String> {

    diff_match_patch dmp = new diff_match_patch();

    @Override
    public IListCompareResult<String, String> compare(List<? extends String> source, List<? extends String> target) {
//        dmp.diff_main(null, null);
        return null;
    }

}
