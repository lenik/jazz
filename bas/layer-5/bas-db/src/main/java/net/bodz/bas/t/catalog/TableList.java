package net.bodz.bas.t.catalog;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class TableList
        implements
            Iterable<ITableMetadata> {

    List<ITableMetadata> list;

    public TableList() {
        this.list = new ArrayList<>();
    }

    public TableList(List<ITableMetadata> list) {
        this.list = list;
    }

    @Override
    public Iterator<ITableMetadata> iterator() {
        return list.iterator();
    }

    public int size() {
        return list.size();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public ITableMetadata get(int index) {
        return list.get(index);
    }

    public boolean add(ITableMetadata table) {
        if (table == null)
            throw new NullPointerException("table");
        return list.add(table);
    }

    public boolean remove(ITableMetadata table) {
        return list.remove(table);
    }

    public void clear() {
        list.clear();
    }

    public List<QualifiedTableName> getQNames() {
        List<QualifiedTableName> qNames = new ArrayList<>(list.size());
        for (ITableMetadata table : this)
            qNames.add(table.getQName());
        return qNames;
    }

    public TableList sortInCreationOrder(ITableDirectory directory) {
        TableOrderRun run = new TableOrderRun(directory);
        for (ITableMetadata table : this)
            run.deepFirst(table);
        return new TableList(run.orderedList);
    }

    public TableList sortInDeletionOrder(ITableDirectory directory) {
        TableList list = sortInCreationOrder(directory);
        Collections.reverse(list.list);
        return list;
    }

    public static final TableList EMPTY = new TableList(Collections.emptyList());

}
