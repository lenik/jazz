package net.bodz.bas.html.viz.util;

import java.io.IOException;

import net.bodz.bas.repr.content.IStreamContent;
import net.bodz.bas.repr.viz.ViewBuilderException;
import net.bodz.bas.repr.viz.web.AbstractHttpViewBuilder;
import net.bodz.bas.repr.viz.web.IHttpViewBuilder;
import net.bodz.bas.servlet.IHttpViewContext;
import net.bodz.bas.servlet.ResourceTransferer;
import net.bodz.bas.std.rfc.mime.ContentType;
import net.bodz.bas.ui.dom1.IUiRef;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class StreamContent_bin
        extends AbstractHttpViewBuilder<IStreamContent>
        implements IHttpViewBuilder<IStreamContent> {

    @Override
    public Class<?> getValueType() {
        return IStreamContent.class;
    }

    @Override
    public ContentType getContentType(HttpServletRequest request, IStreamContent value) {
        return value.getContentType();
    }

    @Override
    public String getEncoding(IStreamContent value) {
        return value.getCharset().name();
    }

    @Override
    public Object buildHttpViewStart(IHttpViewContext ctx, HttpServletResponse resp, IUiRef<IStreamContent> ref)
            throws ViewBuilderException, IOException {
        IStreamContent content = ref.get();
        new ResourceTransferer()//
                .request(ctx)//
                .blob(new StreamContentBlob(content))//
                .asAttachment()//
                .transfer();
        return null;
    }

}
