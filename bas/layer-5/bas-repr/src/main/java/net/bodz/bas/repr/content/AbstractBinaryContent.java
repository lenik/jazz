package net.bodz.bas.repr.content;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import net.bodz.bas.std.rfc.mime.ContentType;
import net.bodz.bas.std.rfc.mime.ContentTypes;

public abstract class AbstractBinaryContent
        extends MutableStreamContent {

    protected static final ContentType DEFAULT_TYPE = ContentTypes.application_octet_stream;

    public AbstractBinaryContent() {
        super(DEFAULT_TYPE);
    }

    public AbstractBinaryContent(String fileName) {
        super(fileName, DEFAULT_TYPE);
    }

    public AbstractBinaryContent(ContentType contentType) {
        super(contentType);
    }

    public AbstractBinaryContent(String fileName, ContentType contentType) {
        super(fileName, contentType);
    }

    @Override
    public Reader newReader()
            throws IOException {
        InputStream in = newInputStream();
        String encoding = getEncoding();
        return new InputStreamReader(in, encoding);
    }

}
