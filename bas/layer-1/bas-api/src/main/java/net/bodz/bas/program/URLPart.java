package net.bodz.bas.program;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;

import net.bodz.bas.err.UnexpectedException;

public class URLPart {

    public static String getArchiveFilePath(URL url) {
        String path = url.getPath();

        int excl = path.lastIndexOf('!');
        path = path.substring("file:".length(), excl);

        try {
            path = URLDecoder.decode(path, "utf-8");
            return path;
        } catch (UnsupportedEncodingException e) {
            throw new UnexpectedException(e.getMessage(), e);
        }
    }

}
