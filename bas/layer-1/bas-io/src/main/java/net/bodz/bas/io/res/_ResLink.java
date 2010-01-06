package net.bodz.bas.io.res;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.Charset;


public abstract class _ResLink implements ResLink {

    @Override
    public Reader openReader(String encoding) throws IOException {
        Charset charset = encoding == null ? null : Charset.forName(encoding);
        return openReader(charset);
    }

    @Override
    public Reader openReader(Charset charset) throws IOException {
        InputStream in = openInputStream();
        Reader reader;
        if (charset == null)
            reader = new InputStreamReader(in);
        else
            reader = new InputStreamReader(in, charset);
        return reader;
    }

    @Override
    public Writer openWriter(boolean append, String encoding) throws IOException {
        Charset charset = encoding == null ? null : Charset.forName(encoding);
        return openWriter(append, charset);
    }

    @Override
    public Writer openWriter(boolean append, Charset charset) throws IOException {
        OutputStream out = openOutputStream(append);
        Writer writer;
        if (charset == null)
            writer = new OutputStreamWriter(out);
        else
            writer = new OutputStreamWriter(out, charset);
        return writer;
    }

}
