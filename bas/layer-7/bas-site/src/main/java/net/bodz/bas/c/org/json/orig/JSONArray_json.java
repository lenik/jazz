package net.bodz.bas.c.org.json.orig;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.bodz.bas.repr.viz.ViewBuilderException;
import net.bodz.bas.servlet.viz.AbstractHttpViewBuilder;
import net.bodz.bas.servlet.viz.IHttpViewContext;
import net.bodz.bas.std.rfc.mime.ContentType;
import net.bodz.bas.std.rfc.mime.ContentTypes;
import net.bodz.bas.ui.dom1.IUiRef;
import net.bodz.json.JSONArray;

public class JSONArray_json
        extends AbstractHttpViewBuilder<JSONArray> {

    public JSONArray_json() {
        super(JSONArray.class);
    }

    @Override
    public ContentType getContentType(HttpServletRequest request, JSONArray value) {
        return ContentTypes.application_json;
    }

    @Override
    public Object buildHttpViewStart(IHttpViewContext ctx, HttpServletResponse resp, IUiRef<JSONArray> ref)
            throws ViewBuilderException, IOException {
        JSONArray jsonArray = ref.get();
        String json = jsonArray.toString();
        PrintWriter writer = resp.getWriter();
        writer.print(json);
        return null;
    }

}
