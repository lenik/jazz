package net.bodz.bas.db.ibatis.sql;

public class SelectionRange
        extends AbstractSelectionRange<SelectionRange> {

    public SelectionRange() {
        super();
    }

    public SelectionRange(long offset, long limit) {
        super(offset, limit);
    }

    public SelectionRange(long offset) {
        super(offset);
    }

}
