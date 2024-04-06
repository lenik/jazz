package net.bodz.bas.fmt.records;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.json.IJsonForm;
import net.bodz.bas.fmt.json.IJsonOut;
import net.bodz.bas.fmt.json.JsonFormOptions;
import net.bodz.bas.json.JsonObject;
import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;

public class CsvRow
        extends ArrayList<String>
        implements
            IJsonForm {

    private static final long serialVersionUID = 1L;

    static final Logger logger = LoggerFactory.getLogger(CsvRow.class);

    String rawLine;

    private String[] fieldNames;
    private Map<String, Integer> fieldNameIndex;

    private boolean strict = false;
    private String defaultValue;
    private String undefinedValue;

    public CsvRow() {
        super();
    }

    public CsvRow(int initialCapacity) {
        super(initialCapacity);
    }

    public String getRawLine() {
        return rawLine;
    }

    public String[] getFieldNames() {
        return fieldNames;
    }

    public void setFieldNames(String... fieldNames) {
        this.fieldNames = fieldNames;
    }

    @Deprecated
    public Map<String, Integer> getFieldNameIndex() {
        return fieldNameIndex;
    }

    public void setFieldNameIndex(Map<String, Integer> fieldNameIndex) {
        this.fieldNameIndex = fieldNameIndex;
    }

    public void headerRow(CsvRow header) {
        headerRow(header, 0);
    }

    public void headerRow(CsvRow header, int startFieldIndex) {
        if (header == null)
            throw new NullPointerException("header");
        int n = header.size(); // - startFieldIndex;
        if (n < 0)
            n = 0;
        fieldNames = new String[n];
        fieldNameIndex = new LinkedHashMap<String, Integer>();
        int i = startFieldIndex;
        for (String name : header) {
            fieldNames[i] = name;
            fieldNameIndex.put(name, i);
            i++;
        }
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

    public Map<String, String> toMap() {
        if (fieldNames == null)
            throw new IllegalStateException("fieldNames wasn't set.");
        Map<String, String> map = new HashMap<>();
        int nDecl = fieldNames.length;
        int nDef = Math.min(nDecl, size());
        for (int i = 0; i < nDef; i++) {
            String field = fieldNames[i];
            String cell = get(i);
            map.put(field, cell);
        }
        return map;
    }

    @Override
    public void jsonIn(JsonObject o, JsonFormOptions opts)
            throws ParseException {
        if (fieldNameIndex == null) {
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
                Integer index = fieldNameIndex.get(field);
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
    public void jsonOut(IJsonOut out, JsonFormOptions opts) {
        if (fieldNameIndex == null) {
            out.array();
            int n = size();
            for (int i = 0; i < n; i++)
                out.value(get(i));
            out.endArray();
        } else {
            out.object();
            for (Entry<String, Integer> entry : fieldNameIndex.entrySet()) {
                String field = entry.getKey();
                int index = entry.getValue();
                String value = get(index);
                out.entry(field, value);
            }
            out.endObject();
        }
    }

}
