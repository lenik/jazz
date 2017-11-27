package net.bodz.lily.util.ajax;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.json.JSONObject;

import net.bodz.bas.c.reflect.NoSuchPropertyException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.potato.PotatoTypes;
import net.bodz.bas.potato.element.IPropertyAccessor;
import net.bodz.bas.potato.element.IType;
import net.bodz.bas.repr.form.FormDeclBuilder;
import net.bodz.bas.repr.form.MutableFormDecl;
import net.bodz.bas.repr.form.PathField;
import net.bodz.bas.repr.form.PathFieldList;

public class TableData {

    Class<?> objectType;
    Map<String, IPropertyAccessor> colmap = new LinkedHashMap<>();
    Map<String, String> formats = new HashMap<>();
    List<?> list;

    public TableData(Class<?> objectType) {
        this.objectType = objectType;
    }

    public List<String> getColumnList() {
        List<String> cols = new ArrayList<>(colmap.keySet());
        return cols;
    }

    public void parseColumnList(List<String> columns)
            throws ParseException, NoSuchPropertyException {
        colmap.clear();

        IType type = PotatoTypes.getInstance().loadType(objectType);
        MutableFormDecl formDecl = new FormDeclBuilder().build(type);
        PathFieldList list = new PathFieldList();

        for (String col : columns) {
            list.parseAndAdd(formDecl, col);
        }

        for (PathField pathField : list)
            colmap.put(pathField.getPath(), pathField);
    }

    public void parseColumnsString(String columns)
            throws NoSuchPropertyException, ParseException {
        List<String> columnList = new ArrayList<>();
        for (String col : columns.split(",")) {
            col = col.trim();
            if (col.isEmpty())
                continue;
            columnList.add(col);
        }
        parseColumnList(columnList);
    }

    public Map<String, String> getFormats() {
        return formats;
    }

    public void setFormats(Map<String, String> formats) {
        if (formats == null)
            throw new NullPointerException("formats");
        this.formats = formats;
    }

    public void setFormat(String column, String format) {
        if (column == null)
            throw new NullPointerException("column");
        if (format == null)
            throw new NullPointerException("format");
        formats.put(column, format);
    }

    public void parseFormats(String json) {
        JSONObject obj = new JSONObject(json);
        for (String key : (Set<String>) obj.keySet()) {
            String format = obj.getString(key);
            formats.put(key, format);
        }
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
            IPropertyAccessor property = colmap.get(col);
            if (property == null)
                throw new IllegalArgumentException("Invalid column name: " + col);

            Object value = property.getValue(obj);

            String format = formats.get(col);
            if (format != null && value != null)
                value = JsonValueFormat.format(property.getPropertyType(), format, value);

            row.add(value);
        }
        return row;
    }

}
