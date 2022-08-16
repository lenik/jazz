package net.bodz.bas.t.catalog;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import net.bodz.bas.c.object.IdentityHashSet;
import net.bodz.bas.err.NoSuchKeyException;

@ProcedureClass
public class TableOrderProc {

    ITableDirectory directory;
    Connection autoLoadConnection;

    Set<ITableMetadata> markSet = new IdentityHashSet<>();
    List<ITableMetadata> orderedList = new ArrayList<>();

    public TableOrderProc(ITableDirectory directory) {
        this(directory, null);
    }

    public TableOrderProc(ITableDirectory directory, Connection autoLoadConnection) {
        this.directory = directory;
        this.autoLoadConnection = autoLoadConnection;
    }

    public List<ITableMetadata> getOrderedList() {
        return orderedList;
    }

    public TableOrderProc traverse(ITableMetadata table) {
        deepFirst(table);
        return this;
    }

    public TableOrderProc traverse(TableOid tableName) {
        ITableMetadata table = directory.getTable(tableName);
        deepFirst(table);
        return this;
    }

    public void reverse() {
        Collections.reverse(orderedList);
    }

    void deepFirst(ITableMetadata table) {
        if (table == null)
            throw new NullPointerException("table");
        if (!markSet.add(table))
            return;
        for (TableOid parentName : table.getParentTableNames()) {
            ITableMetadata parent;
            if (autoLoadConnection == null) {
                parent = directory.getTable(parentName);
            } else {
                parent = directory.autoLoadTableFromJDBC(parentName, autoLoadConnection);
            }
            if (parent == null)
                throw new NoSuchKeyException("Unknown parent: " + parentName);

            deepFirst(parent);
        }
        orderedList.add(table);
    }

}
