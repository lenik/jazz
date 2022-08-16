package net.bodz.bas.t.catalog;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class TableViewList
        extends ArrayList<ITableViewMetadata> {

    private static final long serialVersionUID = 1L;

    public TableViewList() {
        super();
    }

    public TableViewList(Collection<? extends ITableViewMetadata> c) {
        super(c);
    }

    public List<TableOid> id() {
        List<TableOid> ids = new ArrayList<>(size());
        for (ITableViewMetadata view : this)
            ids.add(view.getId());
        return ids;
    }

    public static final TableViewList empty() {
        return new TableViewList();
    }

}
