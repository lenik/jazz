package net.bodz.bas.c.org.json.orig;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import net.bodz.bas.repr.viz.ViewBuilderException;
import net.bodz.bas.repr.viz.web.AbstractHttpViewBuilder;
import net.bodz.bas.servlet.IHttpViewContext;
import net.bodz.bas.std.rfc.mime.ContentType;
import net.bodz.bas.std.rfc.mime.ContentTypes;
import net.bodz.bas.ui.dom1.IUiRef;
import net.bodz.fork.org.json.JSONStringer;

public class JSONStringer_json
        extends AbstractHttpViewBuilder<JSONStringer> {

    public JSONStringer_json() {
        super(JSONStringer.class);
    }

    @Override
    public ContentType getContentType(HttpServletRequest request, JSONStringer value) {
        return ContentTypes.application_json;
    }

    @Override
    public Object buildHttpViewStart(IHttpViewContext ctx, HttpServletResponse resp, IUiRef<JSONStringer> ref)
            throws ViewBuilderException, IOException {
        JSONStringer js = ref.get();
        String json = js.toString();
        PrintWriter writer = resp.getWriter();
        writer.print(json);
        return null;
    }

}
