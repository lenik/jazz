package net.bodz.bas.fmt.json;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONWriter;

import net.bodz.bas.fmt.json.IJsonSerializable;
import net.bodz.bas.http.viz.AbstractHttpViewBuilder;
import net.bodz.bas.http.viz.IHttpViewContext;
import net.bodz.bas.repr.viz.ViewBuilderException;
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
        IJsonSerializable obj = ref.get();
        PrintWriter writer = resp.getWriter();
        JSONWriter jsonWriter = new JSONWriter(writer);
        obj.writeObject(jsonWriter);
        return null;
    }

}
