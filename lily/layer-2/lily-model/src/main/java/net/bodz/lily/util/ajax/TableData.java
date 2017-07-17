package net.bodz.lily.util.ajax;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import net.bodz.bas.potato.PotatoTypes;
import net.bodz.bas.potato.element.IProperty;
import net.bodz.bas.potato.element.IType;

public class TableData {

    Class<?> objectType;
    Map<String, IProperty> colmap;
    List<?> list;

    public TableData(Class<?> objectType, List<String> columns) {
        this.objectType = objectType;

        IType type = PotatoTypes.getInstance().forClass(objectType);
        colmap = new LinkedHashMap<>();
        for (String col : columns) {
            IProperty property = type.getProperty(col);
            if (property == null)
                throw new IllegalArgumentException("No such column: " + col);

            colmap.put(col, property);
        }
    }

    public List<String> getColumnList() {
        List<String> cols = new ArrayList<>(colmap.keySet());
        return cols;
    }

    public List<?> getList() {
        return list;
    }

    public void setList(List<?> list) {
        this.list = list;
    }

    public List<?> convert(Object obj, List<String> columns)
            throws ReflectiveOperationException {
        List<Object> row = new ArrayList<>(columns.size());
        for (String col : columns) {
            IProperty property = colmap.get(col);
            if (property == null)
                throw new IllegalArgumentException("Invalid column name: " + col);

            Object value = property.getValue(obj);
            row.add(value);
        }
        return row;
    }

}
