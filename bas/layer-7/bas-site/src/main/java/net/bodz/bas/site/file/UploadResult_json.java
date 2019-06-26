package net.bodz.bas.site.file;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.bodz.bas.c.org.json.JsonWriter;
import net.bodz.bas.http.viz.AbstractHttpViewBuilder;
import net.bodz.bas.http.viz.IHttpViewContext;
import net.bodz.bas.repr.viz.ViewBuilderException;
import net.bodz.bas.std.rfc.mime.ContentType;
import net.bodz.bas.std.rfc.mime.ContentTypes;
import net.bodz.bas.ui.dom1.IUiRef;

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
        {
            jout.key("files");
            jout.array();
            for (UploadedFileInfo item : result) {
                jout.object();
                item.writeObject(jout);
                jout.endObject();
            }
            jout.endArray();
        }
        jout.endObject();

        return ctx.stop();
    }

}