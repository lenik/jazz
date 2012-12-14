package net.bodz.bas.c.java.net;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;

import net.bodz.bas.err.UnexpectedException;
import net.bodz.bas.std.rfc.mime.ByteArrayContent;
import net.bodz.bas.std.rfc.mime.ContentType;
import net.bodz.bas.std.rfc.mime.IContent;
import net.bodz.bas.std.rfc.mime.StringContent;

public class ContentURL {

    public static URL create(String path, String string) {
        StringContent content = new StringContent(string);
        return create(path, content);
    }

    public static URL create(String path, String contentType, String string) {
        ContentType _contentType = ContentType.forName(contentType);
        StringContent content = new StringContent(_contentType, string);
        return create(path, content);
    }

    public static URL create(String path, byte[] bytes) {
        ByteArrayContent content = new ByteArrayContent(bytes);
        return create(path, content);
    }

    public static URL create(String path, String contentType, byte[] bytes) {
        ContentType _contentType = ContentType.forName(contentType);
        ByteArrayContent content = new ByteArrayContent(_contentType, bytes);
        return create(path, content);
    }

    public static URL create(String path, final IContent content) {
        if (path == null)
            throw new NullPointerException("path");
        if (!path.startsWith("/"))
            throw new IllegalArgumentException("Path isn't begin with /: " + path);
        try {
            return new URL("c", null, 0, path, new URLStreamHandler() {
                @Override
                protected URLConnection openConnection(URL url)
                        throws IOException {
                    return new ContentURLConnection(url, content);
                }
            });
        } catch (MalformedURLException e) {
            throw new UnexpectedException(e.getMessage(), e);
        }
    }

}
