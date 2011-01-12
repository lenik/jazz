package net.bodz.bas.util.file;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

public class FileURL {

    public static URL getURL(File file) {
        URI uri = file.toURI();
        try {
            return uri.toURL();
        } catch (MalformedURLException e) {
            return null;
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
            throw new RuntimeException(e);
        }
    }

    /**
     * if url is an entry of a jar file, then the jar file is returned.
     * 
     * @return the accessible file part of url
     */
    public static File getFile(URL url)
            throws MalformedURLException {
        if (url == null)
            return null;
        String protocol = url.getProtocol();
        if ("jar".equals(protocol))
            return getJarFile(url);
        return FilePath.canoniOf(url);
    }

}
