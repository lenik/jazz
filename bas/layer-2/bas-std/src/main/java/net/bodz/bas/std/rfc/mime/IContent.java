package net.bodz.bas.std.rfc.mime;

import net.bodz.bas.io.res.IStreamInputSourceWrapper;

public interface IContent
        extends IStreamInputSourceWrapper {

    ContentType getContentType();

    /**
     * Length in bytes not in chars.
     */
    Long getContentLength();

    String getCharset();

}
