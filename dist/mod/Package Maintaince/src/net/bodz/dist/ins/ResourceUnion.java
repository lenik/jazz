package net.bodz.dist.ins;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.net.MalformedURLException;
import java.net.URL;

import net.bodz.bas.io.Files;
import net.bodz.bas.lang.err.UnexpectedException;

public class ResourceUnion {

    static final int   T_URL  = 1;
    static final int   T_FILE = 2;

    private final int  type;
    private final URL  url;
    private final File file;

    public ResourceUnion(URL url) {
        this.type = T_URL;
        this.url = url;
        File file = null;
        try {
            file = Files.getFile(url);
        } catch (MalformedURLException e) {
        }
        this.file = file;
    }

    public ResourceUnion(File file) {
        this.type = T_FILE;
        this.file = file;
        this.url = Files.getURL(file);
    }

    public URL getURL() {
        return url;
    }

    public File getFile() {
        return file;
    }

    public InputStream openInputStream() throws IOException {
        switch (type) {
        case T_URL:
            return url.openStream();
        case T_FILE:
            return new FileInputStream(file);
        }
        throw new UnexpectedException();
    }

    public OutputStream openOutputStream(boolean append) throws IOException {
        if (file == null)
            return null;
        return new FileOutputStream(file, append);
    }

    public Reader openReader(String encoding) throws IOException {
        InputStream in = openInputStream();
        if (in == null)
            return null;
        return new InputStreamReader(in, encoding);
    }

    public Writer openWriter(boolean append, String encoding)
            throws IOException {
        OutputStream out = openOutputStream(append);
        if (out == null)
            return null;
        return new OutputStreamWriter(out, encoding);
    }

    @Override
    public String toString() {
        return "Res(file=" + file + ", url=" + url + ")";
    }

}
