package net.bodz.bas.c.java.io;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

import net.bodz.bas.err.UnexpectedException;

public class FileURL {

    public static URL getURL(String pathname, URL fallback) {
        File file = new File(pathname);
        return getURL(file, fallback);
    }

    public static URL getURL(File file, URL fallback) {
        URI uri = file.toURI();
        try {
            return uri.toURL();
        } catch (MalformedURLException e) {
            // throw new UnexpectedException("Can't convert File to URL", e);
            return fallback;
        }
    }

    static File getJarFile(URL jarURL) {
        assert "jar".equals(jarURL.getProtocol());
        String s = jarURL.getPath(); // path = file:/...!...
        assert s.startsWith("file:");
        int excl = s.lastIndexOf('!');
        if (excl != -1) // assert
            s = s.substring(0, excl); // path = file:/...
        try {
            URL truncatedURL = new URL(s);
            return FilePath.canoniOf(truncatedURL);
        } catch (MalformedURLException e) {
            throw new UnexpectedException(e);
        }
    }

    /**
     * if url is an entry of a jar file, then the jar file is returned.
     * 
     * @return the accessible file part of url
     */
    public static File getFile(URL url) {
        if (url == null)
            return null;
        String protocol = url.getProtocol();
        if ("jar".equals(protocol))
            return getJarFile(url);
        return FilePath.canoniOf(url);
    }

    public static File getFile(URL url, String removeSubPath) {
        if (removeSubPath == null || removeSubPath.isEmpty())
            return getFile(url);

        // jar:file:/C:/abc/dir/example.jar!/com/example/Name.class

        String s = url.toExternalForm();
        // s=file:/C:/abc/dir/example.jar!/com/example/Name.class
        if (!s.endsWith(removeSubPath))
            throw new IllegalArgumentException(String.format("URL isn\'t end with %s: %s", removeSubPath, url));

        int rlen = removeSubPath.length();
        // s=file:/C:/abc/dir/example.jar!/, or file:/C:/abc/dir/
        s = s.substring(0, s.length() - rlen);

        String protocol = url.getProtocol();
        if ("jar".equals(protocol)) {
            // the jar URL is verified by above. so now can safely return.
            File jarFile = getJarFile(url);
            return jarFile;
        }

        URL truncatedURL;
        try {
            truncatedURL = new URL(s);
        } catch (MalformedURLException e) {
            throw new UnexpectedException("Truncated url became invalid: " + s, e);
        }
        File dir = FilePath.canoniOf(truncatedURL);
        return dir;
    }

}
