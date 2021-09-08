package net.bodz.bas.repr.content;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

import net.bodz.bas.std.rfc.mime.ContentType;

public class StringContent
        extends AbstractTextContent {

    String string;

    public StringContent(String string) {
        super();
        this.string = string;
    }

    public StringContent(ContentType contentType, String string) {
        super(contentType);
        this.string = string;
    }

    @Override
    public Reader newReader()
            throws IOException {
        return new StringReader(string);
    }

}
