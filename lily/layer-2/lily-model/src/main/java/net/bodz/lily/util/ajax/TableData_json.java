package net.bodz.lily.util.ajax;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.bodz.bas.fmt.json.IJsonOut;
import net.bodz.bas.http.ctx.CurrentHttpService;
import net.bodz.bas.http.viz.IHttpViewContext;
import net.bodz.bas.repr.viz.ViewBuilderException;
import net.bodz.bas.site.ajax.AbstractJsonViewBuilder;
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
                        for (Object val : row)
                            out.value(val);
                        out.endArray();
                    } else {
                        out.object();
                        for (int i = 0; i < ncol; i++) {
                            out.key(columns.get(i));
                            out.value(row.get(i));
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

}
