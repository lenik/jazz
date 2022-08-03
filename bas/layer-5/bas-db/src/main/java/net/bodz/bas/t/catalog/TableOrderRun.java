package net.bodz.bas.t.catalog;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import net.bodz.bas.c.object.IdentityHashSet;

public class TableOrderRun {

    ICatalogMetadata catalog;
    Set<ITableMetadata> markSet = new IdentityHashSet<>();
    List<ITableMetadata> orderedList = new ArrayList<>();

    public TableOrderRun(ICatalogMetadata catalog) {
        this.catalog = catalog;
    }

    public List<ITableMetadata> getOrderedList() {
        return orderedList;
    }

    public TableOrderRun traverse(ITableMetadata table) {
        deepFirst(table);
        return this;
    }

    public TableOrderRun traverse(QualifiedTableName tableName) {
        ITableMetadata table = catalog.getTable(tableName);
        deepFirst(table);
        return this;
    }

    public void reverse() {
        Collections.reverse(orderedList);
    }

    void deepFirst(ITableMetadata table) {
        if (!markSet.add(table))
            return;
        for (QualifiedTableName parentName : table.getParentTableNames()) {
            ITableMetadata parent = catalog.getTable(parentName);
            deepFirst(parent);
        }
        orderedList.add(table);
    }

}
