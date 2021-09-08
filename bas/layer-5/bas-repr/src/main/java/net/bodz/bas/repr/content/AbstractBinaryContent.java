package net.bodz.bas.repr.content;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import net.bodz.bas.std.rfc.mime.ContentType;
import net.bodz.bas.std.rfc.mime.ContentTypes;

public abstract class AbstractBinaryContent
        extends MutableStreamContent {

    public AbstractBinaryContent() {
        super(ContentTypes.application_octet_stream);
    }

    public AbstractBinaryContent(ContentType contentType) {
        super(contentType);
    }

    @Override
    public Reader newReader()
            throws IOException {
        InputStream in = newInputStream();
        String encoding = getEncoding();
        return new InputStreamReader(in, encoding);
    }

}
