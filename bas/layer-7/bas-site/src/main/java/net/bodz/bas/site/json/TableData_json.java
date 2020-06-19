package net.bodz.bas.site.json;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.bodz.bas.fmt.json.IJsonOut;
import net.bodz.bas.fmt.json.IJsonSerializable;
import net.bodz.bas.repr.viz.ViewBuilderException;
import net.bodz.bas.servlet.ctx.CurrentHttpService;
import net.bodz.bas.servlet.viz.IHttpViewContext;
import net.bodz.bas.t.variant.IVariantMap;
import net.bodz.bas.t.variant.VariantMaps;
import net.bodz.bas.ui.dom1.IUiRef;

public class TableData_json
        extends AbstractJsonViewBuilder<TableData> {

    public TableData_json() {
        super(TableData.class);
    }

    @Override
    protected void buildJsonView(IHttpViewContext ctx, IJsonOut out, IUiRef<TableData> ref)
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
                        int n = columns.size();
                        for (int i = 0; i < n; i++) {
                            String column = columns.get(i);
                            String fmt = data.getFormat(column);
                            Object cell = row.get(i);
                            dumpValue(out, cell, fmt);
                        }
                        out.endArray();
                    } else {
                        out.object();
                        for (int i = 0; i < ncol; i++) {
                            String column = columns.get(i);
                            String fmt = data.getFormat(column);
                            out.key(column);
                            dumpValue(out, row.get(i), fmt);
                        }
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

    void dumpValue(IJsonOut out, Object val, String fmt)
            throws IOException {
        if (val instanceof Collection) {
            out.array();
            for (Object item : (Collection<?>) val) {
                dumpValue(out, item, null);
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
