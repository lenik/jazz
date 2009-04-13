package net.bodz.bas.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;

public abstract class _ResLink implements ResLink {

    @Override
    public Reader openReader(String encoding) throws IOException {
        InputStream in = openInputStream();
        Reader reader;
        if (encoding == null)
            reader = new InputStreamReader(in);
        else
            reader = new InputStreamReader(in, encoding);
        return reader;
    }

    @Override
    public Writer openWriter(boolean append, String encoding)
            throws IOException {
        OutputStream out = openOutputStream(append);
        Writer writer;
        if (encoding == null)
            writer = new OutputStreamWriter(out);
        else
            writer = new OutputStreamWriter(out, encoding);
        return writer;
    }

}
