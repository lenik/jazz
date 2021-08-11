package net.bodz.bas.site.json;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import net.bodz.bas.fmt.json.IJsonOut;
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
    protected void buildJsonView(IHttpViewContext ctx, final IJsonOut out, IUiRef<TableData> ref)
            throws ViewBuilderException, IOException {
        HttpServletRequest req = CurrentHttpService.getRequest();
        IVariantMap<String> q = VariantMaps.fromRequest(req);
        boolean rowArray = "array".equals(q.getString("row"));

        TableData data = ref.get();
        out.object();

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

}
