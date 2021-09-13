package net.bodz.bas.fmt.json;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.bodz.bas.err.FormatException;
import net.bodz.bas.repr.viz.ViewBuilderException;
import net.bodz.bas.repr.viz.web.AbstractHttpViewBuilder;
import net.bodz.bas.servlet.IHttpViewContext;
import net.bodz.bas.std.rfc.mime.ContentType;
import net.bodz.bas.std.rfc.mime.ContentTypes;
import net.bodz.bas.ui.dom1.IUiRef;

public class JsonSerializable_json
        extends AbstractHttpViewBuilder<IJsonSerializable> {

    public JsonSerializable_json() {
        super(IJsonSerializable.class);
    }

    @Override
    public ContentType getContentType(HttpServletRequest request, IJsonSerializable value) {
        return ContentTypes.application_json;
    }

    @Override
    public Object buildHttpViewStart(IHttpViewContext ctx, HttpServletResponse resp, IUiRef<IJsonSerializable> ref)
            throws ViewBuilderException, IOException {
        IJsonSerializable jso = ref.get();
        String json;
        try {
            json = JsonFn.toJson(jso);
        } catch (FormatException e) {
            throw new ViewBuilderException(e.getMessage(), e);
        }
        PrintWriter writer = resp.getWriter();
        writer.print(json);
        return null;
    }

}