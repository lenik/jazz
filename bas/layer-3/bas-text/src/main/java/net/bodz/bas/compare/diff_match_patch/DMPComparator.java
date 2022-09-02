package net.bodz.bas.compare.diff_match_patch;

import java.util.List;

import net.bodz.bas.compare.IListComparator;
import net.bodz.bas.compare.IListCompareResult;
import net.bodz.bas.compare.dmp.CharsComparator;
import net.bodz.bas.compare.dmp.DMPConfig;
import net.bodz.bas.compare.dmp.EditList;
import net.bodz.bas.text.row.CharRow;

public class DMPComparator
        implements
            IListComparator<Character, Character> {

    DMPConfig config = new DMPConfig();
    CharsComparator dmp = new CharsComparator(config);

    @Override
    public IListCompareResult<Character, Character> compare(List<? extends Character> source,
            List<? extends Character> target) {
        CharRow row1 = new CharRow(source, false);
        CharRow row2 = new CharRow(target, false);
        EditList<Character> diffs = dmp.compareByPack(row1, row2);
        return new DMPResult<>(diffs);
    }

}
