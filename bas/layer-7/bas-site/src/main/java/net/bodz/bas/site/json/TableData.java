package net.bodz.bas.site.json;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.json.JSONObject;

import net.bodz.bas.c.reflect.NoSuchPropertyException;
import net.bodz.bas.err.LoaderException;
import net.bodz.bas.err.NotImplementedException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.potato.PotatoTypes;
import net.bodz.bas.potato.element.IPropertyAccessor;
import net.bodz.bas.potato.element.IType;
import net.bodz.bas.repr.form.FormDeclBuilder;
import net.bodz.bas.repr.form.MutableFormDecl;
import net.bodz.bas.repr.form.PathField;
import net.bodz.bas.repr.form.PathFieldList;
import net.bodz.bas.t.variant.IVarMapSerializable;
import net.bodz.bas.t.variant.IVariantMap;

public class TableData
        implements IVarMapSerializable {

    Class<?> objectType;
    Map<String, IPropertyAccessor> colmap = new LinkedHashMap<String, IPropertyAccessor>();
    Map<String, String> formats = new HashMap<String, String>();
    List<?> list;

    public TableData(Class<?> objectType) {
        this.objectType = objectType;
    }

    public List<String> getColumnList() {
        List<String> cols = new ArrayList<String>(colmap.keySet());
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

    public TableData parseColumnsString(String columns)
            throws NoSuchPropertyException, ParseException {
        if (columns == null)
            throw new NullPointerException("columns");
        columns = columns.trim();

        List<String> columnList = new ArrayList<String>();
        for (String col : columns.split(",")) {
            col = col.trim();
            if (col.isEmpty())
                continue;
            columnList.add(col);
        }
        parseColumnList(columnList);
        return this;
    }

    public String getFormat(String column) {
        return formats.get(column);
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

    public TableData parseFormats(String json) {
        JSONObject obj = new JSONObject(json);
        for (String key : (Set<String>) obj.keySet()) {
            String format = obj.getString(key);
            formats.put(key, format);
        }
        return this;
    }

    public List<?> getList() {
        return list;
    }

    public void setList(List<?> list) {
        this.list = list;
    }

    public List<?> convert(Object obj, List<String> columns)
            throws ReflectiveOperationException {
        if (obj == null)
            throw new NullPointerException("obj");

        List<Object> row = new ArrayList<Object>(columns.size());
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

    @Override
    public void readObject(IVariantMap<String> map)
            throws LoaderException {
        String columns = map.getString("columns");
        if (columns == null)
            throw new IllegalArgumentException("Expected request parameter columns.");
        try {
            parseColumnsString(columns);
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid columns specified: \"" + columns + "\": " + e.getMessage(), e);
        }

        String formats = map.getString("formats");
        if (formats != null)
            parseFormats(formats);
    }

    @Override
    public void writeObject(Map<String, Object> map) {
        throw new NotImplementedException();
    }

}
