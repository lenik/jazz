package net.bodz.bas.site.file;

import java.io.IOException;
import java.io.PrintWriter;

import net.bodz.bas.c.org.json.JsonWriter;
import net.bodz.bas.fmt.json.JsonFormOptions;
import net.bodz.bas.repr.viz.ViewBuilderException;
import net.bodz.bas.repr.viz.web.AbstractHttpViewBuilder;
import net.bodz.bas.servlet.IHttpViewContext;
import net.bodz.bas.std.rfc.mime.ContentType;
import net.bodz.bas.std.rfc.mime.ContentTypes;
import net.bodz.bas.ui.dom1.IUiRef;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class UploadResult_json
        extends AbstractHttpViewBuilder<UploadResult> {

    public UploadResult_json() {
        super(UploadResult.class);
    }

    @Override
    public ContentType getContentType(HttpServletRequest request, UploadResult value) {
        return ContentTypes.application_json;
    }

    @Override
    public Object buildHttpViewStart(IHttpViewContext ctx, HttpServletResponse resp, IUiRef<UploadResult> ref)
            throws ViewBuilderException, IOException {
        PrintWriter out = resp.getWriter();
        JsonWriter jout = new JsonWriter(out);
        UploadResult result = ref.get();

        jout.object();
        result.jsonOut(jout, JsonFormOptions.WEB);
        jout.endObject();

        return ctx.stop();
    }

}