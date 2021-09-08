package net.bodz.bas.repr.content;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import net.bodz.bas.std.rfc.mime.ContentType;

public class BytesContent
        extends AbstractBinaryContent {

    byte[] buf;
    int off;
    int len;

    public BytesContent(byte[] buf) {
        this(buf, 0, buf.length);
    }

    public BytesContent(byte[] buf, int off, int len) {
        super();
        this.buf = buf;
        this.off = off;
        this.len = len;
    }

    public BytesContent(ContentType contentType, byte[] buf) {
        this(contentType, buf, 0, buf.length);
    }

    public BytesContent(ContentType contentType, byte[] buf, int off, int len) {
        super(contentType);
        this.buf = buf;
        this.off = off;
        this.len = len;
    }

    @Override
    public InputStream newInputStream()
            throws IOException {
        return new ByteArrayInputStream(buf, off, len);
    }

}
