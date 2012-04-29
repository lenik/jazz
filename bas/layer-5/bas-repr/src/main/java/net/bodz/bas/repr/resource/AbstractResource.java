package net.bodz.bas.repr.resource;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;

public abstract class AbstractResource
        implements IResource {

    @Override
    public String getName() {
        String path = getPath();
        int lastSlash = path.lastIndexOf('/');
        String name;
        if (lastSlash == -1)
            name = path;
        else
            name = path.substring(lastSlash + 1);
        return name;
    }

    private static Charset utf8 = Charset.forName("utf-8");

    protected Charset getCharset() {
        return utf8;
    }

    @Override
    public Reader openText()
            throws IOException {
        InputStream in = openBinary();
        return new InputStreamReader(in);
    }

}
