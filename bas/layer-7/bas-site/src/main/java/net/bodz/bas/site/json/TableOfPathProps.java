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
import net.bodz.bas.err.NotImplementedException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.json.IJsonForm;
import net.bodz.bas.fmt.json.IJsonOut;
import net.bodz.bas.fmt.json.JsonFn;
import net.bodz.bas.fmt.json.JsonFormOptions;
import net.bodz.bas.fmt.xml.IXmlForm;
import net.bodz.bas.json.JsonObject;
import net.bodz.bas.json.JsonObjectBuilder;
import net.bodz.bas.meta.decl.NotNull;
import net.bodz.bas.potato.PotatoTypes;
import net.bodz.bas.potato.element.IPropertyAccessor;
import net.bodz.bas.potato.element.IType;
import net.bodz.bas.potato.element.PropertyReadException;
import net.bodz.bas.repr.form.FormDeclBuilder;
import net.bodz.bas.repr.form.MutableFormDecl;
import net.bodz.bas.repr.form.PropertyChain;
import net.bodz.bas.repr.form.SortOrder;
import net.bodz.bas.site.json.PathMapNode.IVisitor;
import net.bodz.bas.t.variant.IVarMapForm;
import net.bodz.bas.t.variant.IVariantMap;

public class TableOfPathProps
        implements IVarMapForm,
                   IJsonForm,
                   IXmlForm {

    public static final String Q_FOR_DATATABLE = "dt";

    public static final String Q_COLUMNS = "columns";
    public static final String Q_FORMATS = "formats";

    /**
     * Specify the row format
     */
    public static final String Q_ROW_FORMAT = "row";
    public static final String ROW_ARRAY = "array";
    public static final String ROW_OBJECT = "object";// default

    public static final String K_ROW_COUNT = "rowCount";

    public static final String Q_WANT_TOTAL_COUNT = "counting";
    public static final String K_TOTAL_COUNT = "totalCount";

    public static final String Q_SEQ = "seq";

    boolean forDataTable;

    Class<?> objectType;
    boolean defaultAll;
    Map<String, PropertyChain> pathAccessorMap = new LinkedHashMap<>();

    String rowFormat = ROW_ARRAY;
    Map<String, String> formats = new HashMap<String, String>();

    List<?> list;

    boolean wantTotalCount;
    Long totalCount;

    Long seq;

    public TableOfPathProps(Class<?> objectType) {
        this.objectType = objectType;
    }

    public List<String> getColumnList() {
        List<String> cols = new ArrayList<String>(pathAccessorMap.keySet());
        return cols;
    }

    public void parsePathListAsColumns(List<String> propertyPaths)
            throws ParseException, NoSuchPropertyException {
        pathAccessorMap.clear();

        IType type = PotatoTypes.getInstance().loadType(objectType);
        MutableFormDecl formDecl = new FormDeclBuilder().build(type);
        pathAccessorMap = formDecl.resolvePatternsToMap(propertyPaths);
    }

    public TableOfPathProps parsePropertyPathsAsColumns(String paths)
            throws NoSuchPropertyException, ParseException {
        if (paths == null)
            throw new NullPointerException("columns");

        if ("*".equals(paths))
            defaultAll = true;

        List<String> pathList = new ArrayList<String>();
        for (String col : paths.split(",")) {
            col = col.trim();
            if (col.isEmpty())
                continue;
            pathList.add(col);
        }
        parsePathListAsColumns(pathList);
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

    public TableOfPathProps parseFormats(String json) {
        JsonObject obj = JsonObjectBuilder.getInstance().parse(json);
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

    public boolean isWantTotalCount() {
        return wantTotalCount;
    }

    public void setWantTotalCount(boolean wantTotalCount) {
        this.wantTotalCount = wantTotalCount;
    }

    public Long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Long totalCount) {
        this.totalCount = totalCount;
    }

    public List<?> convert(Object obj, List<String> columns)
            throws PropertyReadException {
        if (obj == null)
            return null;

        List<Object> row = new ArrayList<Object>(columns.size());
        for (String col : columns) {
            IPropertyAccessor property = pathAccessorMap.get(col);
            if (property == null)
                throw new IllegalArgumentException("Invalid column name: " + col);

            Object value = property.read(obj);

            String format = formats.get(col);
            if (format != null && value != null)
                value = JsonValueFormat.format(property.getPropertyClass(), format, value);

            row.add(value);
        }
        return row;
    }

    @Override
    public void readObject(IVariantMap<String> map)
            throws ParseException {
        forDataTable = map.getBoolean(Q_FOR_DATATABLE, forDataTable);

        String columns = map.getString(Q_COLUMNS);
        if (columns == null)
            // throw new IllegalArgumentException("Expected request parameter columns.");
            columns = "*";
        try {
            parsePropertyPathsAsColumns(columns);
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid columns specified: \"" + columns + "\": " + e.getMessage(), e);
        }

        String formats = map.getString(Q_FORMATS);
        if (formats != null)
            parseFormats(formats);

        rowFormat = map.getString(Q_ROW_FORMAT, rowFormat);
        wantTotalCount = map.getBoolean(Q_WANT_TOTAL_COUNT, wantTotalCount);

        seq = map.getLong(Q_SEQ, seq);
    }

    @Override
    public void writeObject(Map<String, Object> map) {
        throw new NotImplementedException();
    }

    @Override
    public void jsonIn(@NotNull JsonObject o, JsonFormOptions opts)
            throws ParseException {
    }

    @Override
    public void jsonOut(IJsonOut out, JsonFormOptions opts)
            throws IOException, FormatException {
        List<String> columns = getColumnList();

        if (rowFormat.equals(ROW_ARRAY)) {
            // column header.
            out.key("columns");
            {
                out.array();
                for (String col : pathAccessorMap.keySet()) {
                    if (forDataTable) {
                        out.object();
                        out.key("title");
                    }
                    out.value(col);
                    if (forDataTable)
                        out.endObject();
                }
                out.endArray();
            }
        }

        // selection row count, can be less than table row count.
        out.entry(K_ROW_COUNT, list.size());

        out.key("rows");
        {
            out.array();
            for (Object item : list) {
                boolean arrayFormat = rowFormat.equals(ROW_ARRAY);
                boolean objectFormat = rowFormat.equals(ROW_OBJECT);
                if (defaultAll && objectFormat) {
                    if (item instanceof IJsonForm) {
                        ((IJsonForm) item).jsonOutValue(out, opts);
                        continue;
                    }
                }
                try {
                    List<?> row = convert(item, columns);
                    if (arrayFormat) {
                        writeRowAsArray(out, columns, row, opts);
                    } else {
                        writeRowAsObject(out, columns, row, opts);
                    }
                } catch (PropertyReadException e) {
                    throw new RuntimeException("Error convert to json: " + item, e);
                }
            }
            out.endArray();
        }

        out.entryNotNull(K_TOTAL_COUNT, totalCount);
        out.entryNotNull(Q_SEQ, seq);
    }

    void writeRowAsArray(IJsonOut out, List<String> columns, List<?> row, JsonFormOptions opts)
            throws IOException, FormatException {
        if (row == null) {
            out.value(null);
            return;
        }
        int nColumn = columns.size();
        // int nCell = row.size();
        out.array();
        for (int i = 0; i < nColumn; i++) {
            // String column = columns.get(i);
            // String fmt = data.getFormat(column);
            Object cell = i < nColumn ? row.get(i) : null;
            // out.key(column);
            JsonFn.writeObject(out, cell, opts);
        }
        out.endArray();
    }

    void writeRowAsObject(IJsonOut out, List<String> columns, List<?> row, JsonFormOptions opts)
            throws IOException, FormatException {
        if (row == null) {
            out.value(null);
            return;
        }
        int n = columns.size();
        PathMap<Object> struct = new PathMap<>('.', SortOrder.KEEP);
        for (int i = 0; i < n; i++) {
            String column = columns.get(i);
            // String fmt = data.getFormat(column);
            Object cell = row.get(i);
            struct.setAttribute(column, cell);
        }
        out.object();
        try {
            struct.getRoot().<Exception>accept(new IVisitor<Object, Exception>() {

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
                    JsonFn.writeObject(out, value, opts);
                }

            });
        } catch (IOException | FormatException e) {
            throw e;
        } catch (Exception e) {
            throw new FormatException(e.getMessage(), e);
        }
        out.endObject();
    }

}
