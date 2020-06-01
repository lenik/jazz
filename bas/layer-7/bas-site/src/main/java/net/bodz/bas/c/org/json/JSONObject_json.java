package net.bodz.bas.c.org.json;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import net.bodz.bas.repr.viz.ViewBuilderException;
import net.bodz.bas.servlet.viz.AbstractHttpViewBuilder;
import net.bodz.bas.servlet.viz.IHttpViewContext;
import net.bodz.bas.std.rfc.mime.ContentType;
import net.bodz.bas.std.rfc.mime.ContentTypes;
import net.bodz.bas.ui.dom1.IUiRef;

public class JSONObject_json
        extends AbstractHttpViewBuilder<JSONObject> {

    public JSONObject_json() {
        super(JSONObject.class);
    }

    @Override
    public ContentType getContentType(HttpServletRequest request, JSONObject value) {
        return ContentTypes.application_json;
    }

    @Override
    public Object buildHttpViewStart(IHttpViewContext ctx, HttpServletResponse resp, IUiRef<JSONObject> ref)
            throws ViewBuilderException, IOException {
        JSONObject jsonObj = ref.get();
        String json = jsonObj.toString();
        PrintWriter writer = resp.getWriter();
        writer.print(json);
        return null;
    }

}
