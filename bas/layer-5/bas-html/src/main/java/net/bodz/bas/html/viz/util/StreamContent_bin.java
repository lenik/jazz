package net.bodz.bas.html.viz.util;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.bodz.bas.http.viz.AbstractHttpViewBuilder;
import net.bodz.bas.http.viz.IHttpViewBuilder;
import net.bodz.bas.http.viz.IHttpViewContext;
import net.bodz.bas.repr.content.IStreamContent;
import net.bodz.bas.repr.viz.ViewBuilderException;
import net.bodz.bas.std.rfc.mime.ContentType;
import net.bodz.bas.ui.dom1.IUiRef;

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
        return value.getEncoding();
    }

    @Override
    public Object buildHttpViewStart(IHttpViewContext ctx, HttpServletResponse resp, IUiRef<IStreamContent> ref)
            throws ViewBuilderException, IOException {
        IStreamContent content = ref.get();
        InputStream in = content.newInputStream();
        ServletOutputStream out = resp.getOutputStream();
        byte[] block = new byte[4096];
        int nb;
        try {
            try {
                while ((nb = in.read(block)) != -1) {
                    out.write(block, 0, nb);
                }
            } finally {
                in.close();
            }
        } finally {
            out.close();
        }
        return null;
    }

}
