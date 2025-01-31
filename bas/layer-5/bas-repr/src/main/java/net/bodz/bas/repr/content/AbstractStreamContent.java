package net.bodz.bas.repr.content;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;

public abstract class AbstractStreamContent
        extends AbstractContent
        implements
            IStreamContent {

    private static final long serialVersionUID = 1L;

    String fileName;

    @Override
    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public Reader newReader()
            throws IOException {
        InputStream in = newInputStream();
        Charset charset = getCharset();
        return new InputStreamReader(in, charset);
    }

}
