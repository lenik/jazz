package net.bodz.bas.html.viz;

import net.bodz.bas.std.rfc.mime.ContentType;

public class HtmlViewOptions {

    ContentType contentType;
    Boolean origin;

    public ContentType getContentType() {
        return contentType;
    }

    public void setContentType(ContentType contentType) {
        this.contentType = contentType;
    }

    public Boolean getOrigin() {
        return origin;
    }

    public void setOrigin(Boolean origin) {
        this.origin = origin;
    }

}
