package net.bodz.bas.compare.diff_match_patch;

import java.util.AbstractList;
import java.util.Iterator;

import net.bodz.bas.text.row.IRow;

public class RowList<cell_t>
        extends AbstractList<cell_t> {

    IRow<cell_t> row;

    public RowList(IRow<cell_t> row) {
        this.row = row;
    }

    @Override
    public cell_t get(int index) {
        return row.cellAt(index);
    }

    @Override
    public int size() {
        return row.length();
    }

    @Override
    public Iterator<cell_t> iterator() {
        return row.iterator();
    }

    @Override
    public int indexOf(Object o) {
        return row.indexOf(o);
    }

    @Override
    public int lastIndexOf(Object o) {
        return row.lastIndexOf(o);
    }

}
