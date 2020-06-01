package net.bodz.bas.c.org.json.orig;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONStringer;

import net.bodz.bas.repr.viz.ViewBuilderException;
import net.bodz.bas.servlet.viz.AbstractHttpViewBuilder;
import net.bodz.bas.servlet.viz.IHttpViewContext;
import net.bodz.bas.std.rfc.mime.ContentType;
import net.bodz.bas.std.rfc.mime.ContentTypes;
import net.bodz.bas.ui.dom1.IUiRef;

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
