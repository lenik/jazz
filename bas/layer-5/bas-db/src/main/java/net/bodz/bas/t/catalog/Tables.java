package net.bodz.bas.t.catalog;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Tables {

    public static List<TableOid> id(List<? extends ITableMetadata> list) {
        return TableOid.cut(list);
    }

    public static <T extends ITableMetadata> List<T> sortInCreationOrder(List<T> tableList, ITableDirectory directory,
            Connection autoLoadConnection) {
        TableOrderProc run = new TableOrderProc(directory, autoLoadConnection);
        for (ITableMetadata table : tableList)
            run.deepFirst(table);
        ArrayList<T> copy = new ArrayList<T>();
        for (ITableMetadata _item : run.orderedList) {
            @SuppressWarnings("unchecked")
            T item = (T) _item;
            copy.add(item);
        }
        return copy;
    }

    public static <T extends ITableMetadata> List<T> sortInDeletionOrder(List<T> tableList, ITableDirectory directory,
            Connection autoLoadConnection) {
        List<T> sorted = sortInCreationOrder(tableList, directory, autoLoadConnection);
        Collections.reverse(sorted);
        return sorted;
    }

}
