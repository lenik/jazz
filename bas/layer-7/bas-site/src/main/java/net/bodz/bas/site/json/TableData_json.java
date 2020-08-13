package net.bodz.bas.site.json;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.bodz.bas.fmt.json.IJsonOut;
import net.bodz.bas.fmt.json.IJsonSerializable;
import net.bodz.bas.repr.viz.ViewBuilderException;
import net.bodz.bas.servlet.ctx.CurrentHttpService;
import net.bodz.bas.servlet.viz.IHttpViewContext;
import net.bodz.bas.site.json.PathMapNode.IVisitor;
import net.bodz.bas.t.variant.IVariantMap;
import net.bodz.bas.t.variant.VariantMaps;
import net.bodz.bas.ui.dom1.IUiRef;

public class TableData_json
        extends AbstractJsonViewBuilder<TableData> {

    public TableData_json() {
        super(TableData.class);
    }

    @Override
    protected void buildJsonView(IHttpViewContext ctx, final IJsonOut out, IUiRef<TableData> ref)
            throws ViewBuilderException, IOException {
        HttpServletRequest req = CurrentHttpService.getRequest();
        IVariantMap<String> q = VariantMaps.fromRequest(req);
        boolean rowArray = "array".equals(q.getString("row"));

        TableData data = ref.get();
        out.object();

        List<String> columns = data.getColumnList();
        int ncol = columns.size();
        out.key("columns");
        {
            out.array();
            for (String col : columns)
                out.value(col);
            out.endArray();
        }

        out.key("rows");
        {
            out.array();
            for (Object item : data.getList()) {
                try {
                    List<?> row = data.convert(item, columns);
                    if (rowArray) {
                        out.array();
                        for (int i = 0; i < ncol; i++) {
                            // String column = columns.get(i);
                            // String fmt = data.getFormat(column);
                            Object cell = row.get(i);
                            // out.key(column);
                            dumpObject(out, cell);
                        }
                        out.endArray();
                    } else {
                        PathMap<Object> struct = new PathMap<>('.', false);
                        for (int i = 0; i < ncol; i++) {
                            String column = columns.get(i);
                            // String fmt = data.getFormat(column);
                            Object cell = row.get(i);
                            struct.setAttribute(column, cell);
                        }
                        out.object();
                        struct.getRoot().<IOException> accept(new IVisitor<Object, IOException>() {

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
                                    throws IOException {
                                out.key(key);
                                out.object();
                                child.accept(this);
                                out.endObject();
                            }

                            @Override
                            public void visitAttribute(String key, Object value)
                                    throws IOException {
                                out.key(key);
                                dumpObject(out, value);
                            }

                        });
                        out.endObject();
                    }
                } catch (ReflectiveOperationException e) {
                    throw new ViewBuilderException("Error convert to json: " + item, e);
                }
            }
            out.endArray();
        }

        out.endObject();
    }

    static String propertyHead(String propertyPath) {
        int dot = propertyPath.indexOf('.');
        if (dot == -1)
            return propertyPath;
        else
            return propertyPath.substring(0, dot);
    }

    static String propertyTail(String propertyPath) {
        int dot = propertyPath.indexOf('.');
        if (dot == -1)
            return null;
        else
            return propertyPath.substring(dot + 1);
    }

    /**
     * for null property, dump the object.
     *
     * otherwise, dump object in a nested struct representing the property.
     */
    void dumpObjectNested(IJsonOut out, String property, Object val)
            throws IOException {
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
            throws IOException {
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
