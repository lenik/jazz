package net.bodz.bas.site.file;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;
import org.json.JSONObject;

import net.bodz.bas.html.io.IHtmlOut;
import net.bodz.bas.html.viz.AbstractHtmlViewBuilder;
import net.bodz.bas.html.viz.IHtmlViewContext;
import net.bodz.bas.http.ctx.IAnchor;
import net.bodz.bas.repr.viz.ViewBuilderException;
import net.bodz.bas.std.rfc.mime.ContentType;
import net.bodz.bas.std.rfc.mime.ContentTypes;
import net.bodz.bas.ui.dom1.IUiRef;

public class UploadResult_json
        extends AbstractHtmlViewBuilder<UploadResult> {

    public UploadResult_json() {
        super(UploadResult.class);
    }

    @Override
    public ContentType getContentType(HttpServletRequest request, UploadResult value) {
        return ContentTypes.application_json;
    }

    @Override
    public IHtmlOut buildHtmlViewStart(IHtmlViewContext ctx, IHtmlOut parent, IUiRef<UploadResult> ref)
            throws ViewBuilderException, IOException {
        UploadResult result = ref.get();
        IFilePathMapping mapping = ctx.query(IFilePathMapping.class);

        JSONArray filesArray = new JSONArray();
        for (UploadedFileInfo item : result) {
            JSONObject fileObj = new JSONObject();
            IAnchor a = mapping.getAnchor(UploadHandler.class);
            fileObj.put("name", item.name);
            fileObj.put("size", item.size);
            fileObj.put("url", a + item.url);
            fileObj.put("thumbnail", a + item.thumbnail);
            fileObj.put("deleteUrl", a + item.deleteUrl);
            fileObj.put("deleteType", item.deleteType);
            filesArray.put(fileObj);
        }

        JSONObject obj = new JSONObject();
        obj.put("files", filesArray);

        PrintWriter out = ctx.getResponse().getWriter();
        String json = obj.toString();
        out.println(json);

        return ctx.stop();
    }

}