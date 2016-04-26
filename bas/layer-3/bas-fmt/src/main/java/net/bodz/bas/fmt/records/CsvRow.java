package net.bodz.bas.fmt.records;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class CsvRow
        extends ArrayList<String> {

    private static final long serialVersionUID = 1L;

    private Map<String, Integer> fieldNames;
    private boolean strict = false;
    private String defaultValue;
    private String undefinedValue;

    public CsvRow() {
        super();
    }

    public CsvRow(int initialCapacity) {
        super(initialCapacity);
    }

    public Map<String, Integer> getFieldNames() {
        return fieldNames;
    }

    public void setFieldNames(Map<String, Integer> fieldNames) {
        this.fieldNames = fieldNames;
    }

    public Map<String, Integer> setFieldNamesFromHeaderRow(CsvRow header) {
        return setFieldNamesFromHeaderRow(header, 0);
    }

    public Map<String, Integer> setFieldNamesFromHeaderRow(CsvRow header, int startFieldIndex) {
        if (header == null)
            throw new NullPointerException("header");
        fieldNames = new LinkedHashMap<>();
        for (String name : header)
            fieldNames.put(name, startFieldIndex++);
        return fieldNames;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public String getUndefinedValue() {
        return undefinedValue;
    }

    public void setUndefinedValue(String undefinedValue) {
        this.undefinedValue = undefinedValue;
    }

    @Override
    public String get(int fieldIndex) {
        String value = undefinedValue;
        if (fieldIndex >= 0 && fieldIndex < size())
            value = super.get(fieldIndex);
        else if (strict)
            throw new IndexOutOfBoundsException("fieldIndex=" + fieldIndex);

        if (value == null)
            value = defaultValue;

        return value;
    }

    public Integer getInt(int fieldIndex) {
        String value = get(fieldIndex);
        if (value == null)
            return null;
        else
            return Integer.parseInt(value);
    }

    public Long getLong(int fieldIndex) {
        String value = get(fieldIndex);
        if (value == null)
            return null;
        else
            return Long.parseLong(value);
    }

}
