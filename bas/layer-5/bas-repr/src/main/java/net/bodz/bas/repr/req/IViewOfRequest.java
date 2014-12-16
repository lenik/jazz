package net.bodz.bas.repr.req;

import net.bodz.bas.std.rfc.mime.ContentType;
import net.bodz.bas.t.variant.IVariantMap;

public interface IViewOfRequest {

    String getViewName();

    void setViewName(String viewName);

    /**
     * The MIME type for the desired output.
     *
     * @return Non-<code>null</code> MIME literal.
     */
    ContentType getContentType();

    void setContentType(ContentType contentType);

    IVariantMap<String> getParameters();

}
