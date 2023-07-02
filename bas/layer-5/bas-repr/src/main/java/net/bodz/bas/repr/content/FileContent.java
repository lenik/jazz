package net.bodz.bas.repr.content;

import net.bodz.bas.std.rfc.mime.ContentType;

public class FileContent
        extends BytesContent {

    public FileContent(String fileName, byte[] buf) {
        this(fileName, DEFAULT_TYPE, buf, 0, buf.length);
    }

    public FileContent(String fileName, byte[] buf, int off, int len) {
        this(fileName, DEFAULT_TYPE, buf, off, len);
    }

    public FileContent(String fileName, ContentType contentType, byte[] buf) {
        this(fileName, contentType, buf, 0, buf.length);
    }

    public FileContent(String fileName, ContentType contentType, byte[] buf, int off, int len) {
        super(contentType, buf, off, len);
        setFileName(fileName);
    }

}
