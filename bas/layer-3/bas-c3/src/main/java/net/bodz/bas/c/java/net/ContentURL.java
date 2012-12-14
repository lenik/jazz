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

    public static URL create(String name, String string) {
        StringContent content = new StringContent(string);
        return create(name, content);
    }

    public static URL create(String name, String contentType, String string) {
        ContentType _contentType = ContentType.forName(contentType);
        StringContent content = new StringContent(_contentType, string);
        return create(name, content);
    }

    public static URL create(String name, byte[] bytes) {
        ByteArrayContent content = new ByteArrayContent(bytes);
        return create(name, content);
    }

    public static URL create(String name, String contentType, byte[] bytes) {
        ContentType _contentType = ContentType.forName(contentType);
        ByteArrayContent content = new ByteArrayContent(_contentType, bytes);
        return create(name, content);
    }

    public static URL create(String name, final IContent content) {
        try {
            return new URL("c", null, 0, name, new URLStreamHandler() {
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
