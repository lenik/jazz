package net.bodz.bas.c.org.json.alt;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.bodz.bas.c.org.json.JsonStringer;
import net.bodz.bas.repr.viz.ViewBuilderException;
import net.bodz.bas.servlet.viz.AbstractHttpViewBuilder;
import net.bodz.bas.servlet.viz.IHttpViewContext;
import net.bodz.bas.std.rfc.mime.ContentType;
import net.bodz.bas.std.rfc.mime.ContentTypes;
import net.bodz.bas.ui.dom1.IUiRef;

public class JsonStringer_json
        extends AbstractHttpViewBuilder<JsonStringer> {

    public JsonStringer_json() {
        super(JsonStringer.class);
    }

    @Override
    public ContentType getContentType(HttpServletRequest request, JsonStringer value) {
        return ContentTypes.application_json;
    }

    @Override
    public Object buildHttpViewStart(IHttpViewContext ctx, HttpServletResponse resp, IUiRef<JsonStringer> ref)
            throws ViewBuilderException, IOException {
        JsonStringer js = ref.get();
        String json = js.toString();
        PrintWriter writer = resp.getWriter();
        writer.print(json);
        return null;
    }

}
