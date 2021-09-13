package net.bodz.bas.fmt.json;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.bodz.bas.json.JsonArray;
import net.bodz.bas.repr.viz.ViewBuilderException;
import net.bodz.bas.repr.viz.web.AbstractHttpViewBuilder;
import net.bodz.bas.servlet.IHttpViewContext;
import net.bodz.bas.std.rfc.mime.ContentType;
import net.bodz.bas.std.rfc.mime.ContentTypes;
import net.bodz.bas.ui.dom1.IUiRef;

/**
 * _1 to avoid same names with different case.
 */
public class JsonArray_json
        extends AbstractHttpViewBuilder<JsonArray> {

    public JsonArray_json() {
        super(JsonArray.class);
    }

    @Override
    public ContentType getContentType(HttpServletRequest request, JsonArray value) {
        return ContentTypes.application_json;
    }

    @Override
    public Object buildHttpViewStart(IHttpViewContext ctx, HttpServletResponse resp, IUiRef<JsonArray> ref)
            throws ViewBuilderException, IOException {
        JsonArray jsonArray = ref.get();
        String json = jsonArray.toString();
        PrintWriter writer = resp.getWriter();
        writer.print(json);
        return null;
    }

}
