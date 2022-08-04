package net.bodz.bas.t.catalog;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class TableList
        extends ArrayList<ITableMetadata> {

    private static final long serialVersionUID = 1L;

    public TableList() {
        super();
    }

    public TableList(Collection<? extends ITableMetadata> c) {
        super(c);
    }

    public List<TableId> id() {
        List<TableId> ids = new ArrayList<>(size());
        for (ITableMetadata table : this)
            ids.add(table.getId());
        return ids;
    }

    public TableList sortInCreationOrder(ITableDirectory directory) {
        TableOrderRun run = new TableOrderRun(directory);
        for (ITableMetadata table : this)
            run.deepFirst(table);
        return new TableList(run.orderedList);
    }

    public TableList sortInDeletionOrder(ITableDirectory directory) {
        TableList sorted = sortInCreationOrder(directory);
        Collections.reverse(sorted);
        return sorted;
    }

    public static final TableList empty() {
        return new TableList();
    }

}
