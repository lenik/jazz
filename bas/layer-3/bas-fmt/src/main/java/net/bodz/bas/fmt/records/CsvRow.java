package net.bodz.bas.fmt.records;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.json.IJsonOut;
import net.bodz.bas.fmt.json.IJsonSerializable;
import net.bodz.bas.fmt.json.JsonObject;
import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;

public class CsvRow
        extends ArrayList<String>
        implements IJsonSerializable {

    private static final long serialVersionUID = 1L;

    static final Logger logger = LoggerFactory.getLogger(CsvRow.class);

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
        fieldNames = new LinkedHashMap<String, Integer>();
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
        if (value.isEmpty())
            return null;
        return Integer.parseInt(value);
    }

    public Long getLong(int fieldIndex) {
        String value = get(fieldIndex);
        if (value == null)
            return null;
        if (value.isEmpty())
            return null;
        return Long.parseLong(value);
    }

    public Double getDouble(int fieldIndex) {
        String value = get(fieldIndex);
        if (value == null)
            return null;
        if (value.isEmpty())
            return null;
        return Double.parseDouble(value);
    }

    @Override
    public String set(int fieldIndex, String str) {
        if (fieldIndex >= 0 && fieldIndex < size())
            return super.set(fieldIndex, str);
        if (strict)
            throw new IndexOutOfBoundsException("fieldIndex=" + fieldIndex);
        else
            // jut ignore the value.
            return null;
    }

    @Override
    public void readObject(JsonObject o)
            throws ParseException {
        if (fieldNames == null) {
            int index = 0;
            for (Object key : o.keySet()) {
                String field = (String) key;
                Object value = o.get(field);
                set(index, (String) value);
                index++;
            }
        } else {
            for (Object key : o.keySet()) {
                String field = (String) key;
                Integer index = fieldNames.get(field);
                if (index == null) {
                    logger.warn("Invalid field name: " + key);
                    continue;
                }
                if (o.has(field)) {
                    Object value = o.get(field);
                    set(index, (String) value);
                }
            }
        }
    }

    @Override
    public void writeObject(IJsonOut out) {
        if (fieldNames == null) {
            out.array();
            int n = size();
            for (int i = 0; i < n; i++)
                out.value(get(i));
            out.endArray();
        } else {
            out.object();
            for (Entry<String, Integer> entry : fieldNames.entrySet()) {
                String field = entry.getKey();
                int index = entry.getValue();
                String value = get(index);
                out.entry(field, value);
            }
            out.endObject();
        }
    }

}
