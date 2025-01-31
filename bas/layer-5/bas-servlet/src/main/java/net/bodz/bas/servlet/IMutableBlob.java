package net.bodz.bas.servlet;

import net.bodz.bas.std.rfc.mime.ContentType;
import net.bodz.bas.std.rfc.mime.ContentTypes;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.time.OffsetDateTime;

public interface IMutableBlob
        extends IBlob {

//    void setLocation(String location);
//
//    void setPath(String path);

    void setContentType(ContentType contentType);

    void setCharset(Charset charset);

    default void setCharset(String charsetName) {
        Charset charset = Charset.forName(charsetName);
        setCharset(charset);
    }

//    void setLastModified(OffsetDateTime time);

    void setFilename(String name);

    void setDescription(String description);

}
