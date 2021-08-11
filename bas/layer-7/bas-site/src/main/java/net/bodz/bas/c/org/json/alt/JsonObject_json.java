package net.bodz.bas.c.org.json.alt;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.bodz.bas.json.JsonObject;
import net.bodz.bas.repr.viz.ViewBuilderException;
import net.bodz.bas.servlet.viz.AbstractHttpViewBuilder;
import net.bodz.bas.servlet.viz.IHttpViewContext;
import net.bodz.bas.std.rfc.mime.ContentType;
import net.bodz.bas.std.rfc.mime.ContentTypes;
import net.bodz.bas.ui.dom1.IUiRef;

/**
 * _1 to avoid same names with different case.
 */
public class JsonObject_json
        extends AbstractHttpViewBuilder<JsonObject> {

    public JsonObject_json() {
        super(JsonObject.class);
    }

    @Override
    public ContentType getContentType(HttpServletRequest request, JsonObject value) {
        return ContentTypes.application_json;
    }

    @Override
    public Object buildHttpViewStart(IHttpViewContext ctx, HttpServletResponse resp, IUiRef<JsonObject> ref)
            throws ViewBuilderException, IOException {
        JsonObject jsonObj = ref.get();
        String json = jsonObj.toString();
        PrintWriter writer = resp.getWriter();
        writer.print(json);
        return null;
    }

}
