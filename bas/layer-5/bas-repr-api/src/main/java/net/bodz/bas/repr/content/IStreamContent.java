package net.bodz.bas.repr.content;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

import net.bodz.bas.std.rfc.mime.ContentType;

public interface IStreamContent
        extends
            IContent {

    String getFileName();

    ContentType getContentType();

    Long getContentLength();

    String getEncoding();

    InputStream newInputStream()
            throws IOException;

    Reader newReader()
            throws IOException;

}
