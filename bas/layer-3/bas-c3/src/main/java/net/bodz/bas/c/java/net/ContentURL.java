package net.bodz.bas.c.java.net;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;

import net.bodz.bas.err.UnexpectedException;
import net.bodz.bas.std.rfc.mime.IContent;

public class ContentURL {

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
