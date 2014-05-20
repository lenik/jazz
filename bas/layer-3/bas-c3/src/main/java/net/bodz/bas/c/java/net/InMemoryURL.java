package net.bodz.bas.c.java.net;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;
import java.util.HashMap;
import java.util.Map;

import net.bodz.bas.err.UnexpectedException;
import net.bodz.bas.io.res.IStreamInputSource;
import net.bodz.bas.io.res.builtin.ByteArrayResource;
import net.bodz.bas.io.res.builtin.StringSource;
import net.bodz.bas.std.rfc.mime.ContentType;
import net.bodz.bas.std.rfc.mime.ContentTypes;

public class InMemoryURL {

    public static URL create(String path, String string) {
        return create(path, ContentTypes.text_plain, string);
    }

    public static URL create(String path, ContentType contentType, String string) {
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("Content-Type", contentType.getName());
        return create(path, new StringSource(string), null, headers);
    }

    public static URL create(String path, byte[] bytes) {
        return create(path, ContentTypes.application_octet_stream, bytes);
    }

    public static URL create(String path, ContentType contentType, byte[] bytes) {
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("Content-Type", contentType.getName());
        return create(path, new ByteArrayResource(bytes), (long) bytes.length, headers);
    }

    public static URL create(String path, final IStreamInputSource inputSource, final Long length,
            final Map<String, String> headers) {
        if (path == null)
            throw new NullPointerException("path");
        if (!path.startsWith("/"))
            throw new IllegalArgumentException("Path isn't begin with /: " + path);
        try {
            return new URL("mem", null, 0, path, new URLStreamHandler() {
                @Override
                protected URLConnection openConnection(URL url)
                        throws IOException {
                    return new InMemoryURLConnection(url, inputSource, length, headers);
                }
            });
        } catch (MalformedURLException e) {
            throw new UnexpectedException(e.getMessage(), e);
        }
    }

}
