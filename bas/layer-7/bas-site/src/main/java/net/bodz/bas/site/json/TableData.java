package net.bodz.bas.site.json;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import net.bodz.bas.c.reflect.NoSuchPropertyException;
import net.bodz.bas.err.FormatException;
import net.bodz.bas.err.LoaderException;
import net.bodz.bas.err.NotImplementedException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.json.IJsonOut;
import net.bodz.bas.fmt.json.IJsonSerializable;
import net.bodz.bas.fmt.json.JsonObject;
import net.bodz.bas.fmt.xml.IXmlSerializable;
import net.bodz.bas.potato.PotatoTypes;
import net.bodz.bas.potato.element.IPropertyAccessor;
import net.bodz.bas.potato.element.IType;
import net.bodz.bas.repr.form.FormDeclBuilder;
import net.bodz.bas.repr.form.MutableFormDecl;
import net.bodz.bas.repr.form.PathField;
import net.bodz.bas.repr.form.PathFieldList;
import net.bodz.bas.site.json.PathMapNode.IVisitor;
import net.bodz.bas.t.variant.IVarMapSerializable;
import net.bodz.bas.t.variant.IVariantMap;
import net.bodz.json.JSONObject;

public class TableData
        implements
            IVarMapSerializable,
            IJsonSerializable,
            IXmlSerializable {

    /**
     * Specify the row format
     */
    public static final String K_ROW = "row";
    public static final String ROW_ARRAY = "array";
    public static final String ROW_OBJECT = "object";// default

    Class<?> objectType;
    Map<String, IPropertyAccessor> pathAccessorMap = new LinkedHashMap<String, IPropertyAccessor>();
    Map<String, String> formats = new HashMap<String, String>();
    List<?> list;

    public TableData(Class<?> objectType) {
        this.objectType = objectType;
    }

    public List<String> getColumnList() {
        List<String> cols = new ArrayList<String>(pathAccessorMap.keySet());
        return cols;
    }

    public void parseColumnList(List<String> propertyPaths)
            throws ParseException, NoSuchPropertyException {
        pathAccessorMap.clear();

        IType type = PotatoTypes.getInstance().loadType(objectType);
        MutableFormDecl formDecl = new FormDeclBuilder().build(type);
        PathFieldList pathFieldList = new PathFieldList();

        for (String propertyPath : propertyPaths) {
            pathFieldList.parseAndAdd(formDecl, propertyPath);
        }

        for (PathField pathField : pathFieldList)
            pathAccessorMap.put(pathField.getPath(), pathField);
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
        for (String key : obj.keySet()) {
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
            IPropertyAccessor property = pathAccessorMap.get(col);
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

    @Override
    public void readObject(JsonObject o)
            throws ParseException {
    }

    boolean rowArray;

    @Override
    public void writeObject(IJsonOut out)
            throws IOException, FormatException {
        List<String> columns = getColumnList();
        out.key("columns");
        {
            out.array();
            for (String col : pathAccessorMap.keySet())
                out.value(col);
            out.endArray();
        }

        out.key("rows");
        {
            out.array();
            for (Object item : list) {
                try {
                    List<?> row = convert(item, columns);
                    if (rowArray) {
                        writeRowAsArray(out, columns, row);
                    } else {
                        writeRowAsObject(out, columns, row);
                    }
                } catch (ReflectiveOperationException e) {
                    throw new RuntimeException("Error convert to json: " + item, e);
                }
            }
            out.endArray();
        }

    }

    void writeRowAsArray(IJsonOut out, List<String> columns, List<?> row)
            throws IOException, FormatException {
        int n = columns.size();
        out.array();
        for (int i = 0; i < n; i++) {
            // String column = columns.get(i);
            // String fmt = data.getFormat(column);
            Object cell = row.get(i);
            // out.key(column);
            dumpObject(out, cell);
        }
        out.endArray();
    }

    void writeRowAsObject(IJsonOut out, List<String> columns, List<?> row)
            throws IOException, FormatException {
        int n = columns.size();
        PathMap<Object> struct = new PathMap<>('.', false);
        for (int i = 0; i < n; i++) {
            String column = columns.get(i);
            // String fmt = data.getFormat(column);
            Object cell = row.get(i);
            struct.setAttribute(column, cell);
        }
        out.object();
        try {
            struct.getRoot().<Exception> accept(new IVisitor<Object, Exception>() {

                @Override
                public void combineKeys(List<Collection<String>> keySets) {
                    List<String> sorted = new ArrayList<>();
                    for (Collection<String> keySet : keySets)
                        sorted.addAll(keySet);
                    keySets.clear();
                    keySets.add(sorted);
                }

                @Override
                public void visitNode(String key, PathMapNode<Object> child)
                        throws Exception {
                    out.key(key);
                    out.object();
                    child.accept(this);
                    out.endObject();
                }

                @Override
                public void visitAttribute(String key, Object value)
                        throws IOException, FormatException {
                    out.key(key);
                    dumpObject(out, value);
                }

            });
        } catch (IOException | FormatException e) {
            throw e;
        } catch (Exception e) {
            throw new FormatException(e.getMessage(), e);
        }
        out.endObject();
    }

    /**
     * for null property, dump the object.
     *
     * otherwise, dump object in a nested struct representing the property.
     */
    void dumpObjectNested(IJsonOut out, String property, Object val)
            throws IOException, FormatException {
        if (property != null) {
            PathMap<Object> map = new PathMap<>('.', false);
            int dot = property.indexOf('.');
            String head = property;
            if (dot == -1)
                property = null;
            else
                property = property.substring(dot + 1);
            out.object();
            out.key(head);
            dumpObjectNested(out, property, val);
            out.endObject();
        } else {
            dumpObject(out, val);
        }
    }

    void dumpObject(IJsonOut out, Object val)
            throws IOException, FormatException {
        if (val instanceof Collection) {
            out.array();
            for (Object item : (Collection<?>) val) {
                dumpObject(out, item);
            }
            out.endArray();
            return;
        }
        if (val instanceof IJsonSerializable) {
            IJsonSerializable jsVal = (IJsonSerializable) val;
            out.object();
            jsVal.writeObject(out);
            out.endObject();
            return;
        }
        out.value(val);
    }

}
