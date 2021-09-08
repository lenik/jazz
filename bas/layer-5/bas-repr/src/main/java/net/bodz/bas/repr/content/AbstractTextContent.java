package net.bodz.bas.repr.content;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

import net.bodz.bas.c.java.io.ReaderInputStream;
import net.bodz.bas.std.rfc.mime.ContentType;
import net.bodz.bas.std.rfc.mime.ContentTypes;

public abstract class AbstractTextContent
        extends MutableStreamContent {

    public AbstractTextContent() {
        super(ContentTypes.text_plain);
    }

    public AbstractTextContent(ContentType contentType) {
        super(contentType);
    }

    @Override
    public InputStream newInputStream()
            throws IOException {
        Reader reader = newReader();
        String encoding = getEncoding();
        ReaderInputStream in = new ReaderInputStream(reader, encoding);
        return in;
    }

}
