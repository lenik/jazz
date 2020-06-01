package net.bodz.bas.c.org.json;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.bodz.bas.fmt.json.JsonArray;
import net.bodz.bas.repr.viz.ViewBuilderException;
import net.bodz.bas.servlet.viz.AbstractHttpViewBuilder;
import net.bodz.bas.servlet.viz.IHttpViewContext;
import net.bodz.bas.std.rfc.mime.ContentType;
import net.bodz.bas.std.rfc.mime.ContentTypes;
import net.bodz.bas.ui.dom1.IUiRef;

/**
 * _1 to avoid same names with different case.
 */
public class JsonArray_json_1
        extends AbstractHttpViewBuilder<JsonArray> {

    public JsonArray_json_1() {
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
