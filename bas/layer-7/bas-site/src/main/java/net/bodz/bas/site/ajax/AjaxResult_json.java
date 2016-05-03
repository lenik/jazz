package net.bodz.bas.site.ajax;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONWriter;

import net.bodz.bas.http.viz.AbstractHttpViewBuilder;
import net.bodz.bas.http.viz.IHttpViewContext;
import net.bodz.bas.repr.viz.ViewBuilderException;
import net.bodz.bas.std.rfc.mime.ContentType;
import net.bodz.bas.std.rfc.mime.ContentTypes;
import net.bodz.bas.ui.dom1.IUiRef;

public class AjaxResult_json
        extends AbstractHttpViewBuilder<AjaxResult> {

    public AjaxResult_json() {
        super(AjaxResult.class);
    }

    @Override
    public ContentType getContentType(HttpServletRequest request, AjaxResult value) {
        return ContentTypes.application_json;
    }

    @Override
    public Object buildHttpViewStart(IHttpViewContext ctx, HttpServletResponse resp, IUiRef<AjaxResult> ref)
            throws ViewBuilderException, IOException {
        AjaxResult result = ref.get();
        PrintWriter writer = resp.getWriter();
        JSONWriter jsonWriter = new JSONWriter(writer);
        result.writeObject(jsonWriter);
        return null;
    }

}
