package net.bodz.bas.repr.content;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

import net.bodz.bas.c.java.io.ReaderInputStream;

public abstract class MutableReaderContent
        extends MutableStreamContent {

    @Override
    public InputStream newInputStream()
            throws IOException {
        Reader reader = newReader();
        String encoding = getEncoding();
        ReaderInputStream in = new ReaderInputStream(reader, encoding);
        return in;
    }

}
