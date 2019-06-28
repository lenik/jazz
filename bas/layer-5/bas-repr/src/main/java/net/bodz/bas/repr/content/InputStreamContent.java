package net.bodz.bas.repr.content;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

public abstract class InputStreamContent
        extends MutableStreamContent {

    @Override
    public Reader newReader()
            throws IOException {
        InputStream in = newInputStream();
        String encoding = getEncoding();
        return new InputStreamReader(in, encoding);
    }

}
