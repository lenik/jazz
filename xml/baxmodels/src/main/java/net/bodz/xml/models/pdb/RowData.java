package net.bodz.xml.models.pdb;

import java.util.ArrayList;
import java.util.List;

import net.bodz.bas.types.util.Strings;

import org.w3c.dom.Element;

public class RowData {

    private boolean        allowOutbound = true;

    protected final Table  table;
    protected List<String> values;

    public RowData(Table table) {
        if (table == null)
            throw new NullPointerException("table");
        this.table = table;
        values = new ArrayList<String>(table.getFields().size());
    }

    public String get(int fieldIndex) throws IndexOutOfBoundsException {
        if (fieldIndex >= values.size())
            if (allowOutbound)
                return null;
        return values.get(fieldIndex);
    }

    public void set(int fieldIndex, String value) {
        if (allowOutbound)
            while (fieldIndex >= values.size())
                values.add(null);
        values.set(fieldIndex, value);
    }

    public String get(String fieldName) {
        int index = getFieldIndex(fieldName);
        if (index == -1)
            throw new IllegalArgumentException("nonexist field name: " + fieldName);
        return get(index);
    }

    public void set(String fieldName, String value) {
        int index = getFieldIndex(fieldName);
        if (index == -1)
            throw new IllegalArgumentException("nonexist field name: " + fieldName);
        set(index, value);
    }

    protected String getFieldName(int index) {
        Field field = table.getFields().get(index);
        return field.getName();
    }

    protected int getFieldIndex(String fieldName) {
        if (fieldName == null)
            throw new NullPointerException("fieldName");
        List<Field> fields = table.getFields();
        int n = fields.size();
        for (int i = 0; i < n; i++) {
            Field field = fields.get(i);
            if (fieldName.equals(field.getName()))
                return i;
        }
        return -1;
    }

    public static RowData parse(Table table, Element element) {
        RowData row = new RowData(table);
        return row;
    }

    public Element toElement() {
        return null;
    }

    @Override
    public String toString() {
        int n = values.size();
        StringBuffer buf = new StringBuffer(n * 30);
        for (int index = 0; index < n; index++) {
            String value = values.get(index);
            if (value == null)
                continue;
            String name = table.getFields().get(index).getName();
            if (index != 0)
                buf.append(" ");
            buf.append(name + "=\"" + Strings.escape(value) + "\"");
        }
        return buf.toString();
    }

}
