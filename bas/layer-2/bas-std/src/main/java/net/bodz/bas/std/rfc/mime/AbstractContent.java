package net.bodz.bas.std.rfc.mime;

import java.io.Serializable;
import java.nio.charset.Charset;

import net.bodz.bas.io.resource.IStreamInputSource;

public abstract class AbstractContent
        implements IContent, Serializable {

    private static final long serialVersionUID = 1L;

    private ContentType contentType;
    private String charset = "utf-8";

    public AbstractContent() {
    }

    public AbstractContent(ContentType contentType) {
        if (contentType == null)
            throw new NullPointerException("contentType");
        this.contentType = contentType;
    }

    @Override
    public ContentType getContentType() {
        return contentType;
    }

    public void setContentType(ContentType contentType) {
        if (contentType == null)
            throw new NullPointerException("contentType");
        this.contentType = contentType;
    }

    @Override
    public Long getContentLength() {
        return null;
    }

    @Override
    public String getCharset() {
        return charset;
    }

    public void setCharset(String charset) {
        if (charset == null)
            throw new NullPointerException("charset");
        this.charset = charset;
    }

    @Override
    public IStreamInputSource getInputSource() {
        return getInputSource(charset);
    }

    @Override
    public IStreamInputSource getInputSource(String charsetName) {
        Charset charset = Charset.forName(charsetName);
        return getInputSource(charset);
    }

}
