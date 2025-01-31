package net.bodz.bas.repr.content;

import net.bodz.bas.c.java.nio.Charsets;
import net.bodz.bas.std.rfc.mime.ContentType;

import java.nio.charset.Charset;

public abstract class MutableStreamContent
        extends MutableContent
        implements
            IStreamContent {

    String fileName;
    ContentType contentType;
    Charset charset = Charsets.UTF_8;

    public MutableStreamContent(ContentType contentType) {
        this(null, contentType);
    }

    public MutableStreamContent(String fileName, ContentType contentType) {
        this.fileName = fileName;
        this.contentType = contentType;
    }

    @Override
    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public ContentType getContentType() {
        return contentType;
    }

    public void setContentType(ContentType contentType) {
        this.contentType = contentType;
    }

    @Override
    public Long getContentLength() {
        return null;
    }

    @Override
    public Charset getCharset() {
        return charset;
    }

    public void setCharset(Charset charset) {
        this.charset = charset;
    }

}
