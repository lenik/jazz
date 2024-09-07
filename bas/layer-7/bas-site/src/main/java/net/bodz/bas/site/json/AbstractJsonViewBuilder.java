package net.bodz.bas.site.json;

import java.io.IOException;
import java.io.PrintWriter;

import net.bodz.bas.c.org.json.JsonWriter;
import net.bodz.bas.fmt.json.IJsonOut;
import net.bodz.bas.fmt.json.JsonFormOptions;
import net.bodz.bas.repr.viz.ViewBuilderException;
import net.bodz.bas.repr.viz.web.AbstractHttpViewBuilder;
import net.bodz.bas.servlet.IHttpViewContext;
import net.bodz.bas.std.rfc.mime.ContentType;
import net.bodz.bas.std.rfc.mime.ContentTypes;
import net.bodz.bas.ui.dom1.IUiRef;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public abstract class AbstractJsonViewBuilder<T>
        extends AbstractHttpViewBuilder<T> {

    public AbstractJsonViewBuilder() {
        super();
    }

    public AbstractJsonViewBuilder(Class<?> valueClass) {
        super(valueClass);
    }

    @Override
    public boolean isOrigin(T value) {
        return true;
    }

    @Override
    public boolean isFrame() {
        return true;
    }

    @Override
    public ContentType getContentType(HttpServletRequest request, T value) {
        return ContentTypes.application_json;
    }

    @Override
    public void precompile(IHttpViewContext ctx, IUiRef<T> ref) {
//         Somewhere to configure?
//        HttpServletResponse response = ctx.getResponse();
//        response.addHeader("Access-Control-Allow-Origin", "*");
    }

    @Override
    public Object buildHttpViewStart(IHttpViewContext ctx, HttpServletResponse resp, IUiRef<T> ref)
            throws ViewBuilderException, IOException {
//        IVariantMap<String> q = VariantMaps.fromRequest(ctx.getRequest());
//        JsonFormOptions opts = new JsonFormOptions(q);

        PrintWriter writer = resp.getWriter();
        JsonWriter out = new JsonWriter(writer);

        buildJsonView(ctx, out, ref, JsonFormOptions.WEB);
        return null;
    }

    protected abstract void buildJsonView(IHttpViewContext ctx, IJsonOut out, IUiRef<T> ref, JsonFormOptions opts)
            throws ViewBuilderException, IOException;

}
