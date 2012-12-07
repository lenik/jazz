package net.bodz.bas.repr.req;

import net.bodz.bas.util.variant.IVariantLookupMap;
import net.bodz.bas.vfs.util.ContentType;

public interface IRequestView {

    String ATTRIBUTE_KEY = IRequestView.class.getCanonicalName();

    String getViewName();

    void setViewName(String viewName);

    /**
     * The MIME type for the desired output.
     * 
     * @return Non-<code>null</code> MIME literal.
     */
    ContentType getContentType();

    void setContentType(ContentType contentType);

    IVariantLookupMap<String> getParameters();

}
