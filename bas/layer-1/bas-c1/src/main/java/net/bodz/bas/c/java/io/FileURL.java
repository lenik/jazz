package net.bodz.bas.c.java.io;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Map.Entry;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import net.bodz.bas.err.UnexpectedException;
import net.bodz.bas.t.pojo.Pair;

public class FileURL {

    public static URL toURL(String pathname) {
        File file = new File(pathname);
        return toURL(file);
    }

    public static URL toURL(String pathname, URL fallback) {
        File file = new File(pathname);
        return toURL(file, fallback);
    }

    public static URL toURL(File file) {
        URI uri = file.toURI();
        try {
            return uri.toURL();
        } catch (MalformedURLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public static URL toURL(File file, URL fallback) {
        URI uri = file.toURI();
        try {
            return uri.toURL();
        } catch (MalformedURLException e) {
            // throw new UnexpectedException("Can't convert File to URL", e);
            return fallback;
        }
    }

    /**
     * Convert the url to file is possible, otherwise return <code>null</code>.
     *
     * @return The corresponding file, or <code>null</code> if not available.
     */
    public static File toFile(URL url, File fallback) {
        if (url == null)
            return null;

        String protocol = url.getProtocol();
        if ("file".equals(protocol)) {
            URI uri;
            try {
                uri = url.toURI();
            } catch (URISyntaxException e) {
                throw new IllegalArgumentException(e.getMessage(), e);
            }
            return new File(uri);
        }
        return fallback;
    }

    /**
     * If url is an entry of a jar file, then the jar file is returned.
     *
     * @return The nearest local file for the url. If no such file exists, returns <code>null</code>
     */
    public static File toNearestFile(URL url) {
        if (url == null)
            return null;

        String protocol = url.getProtocol();
        if ("file".equals(protocol))
            return new File(url.getFile());

        if ("jar".equals(protocol))
            return getJarFile(url);

        return null;
    }

    public static File toNearestFile(URL url, String removeSubPath, File fallback) {
        if (removeSubPath == null || removeSubPath.isEmpty())
            return toFile(url, fallback);

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

    public static Long length(URL url, Long notExisted)
            throws IOException {
        if (url == null)
            throw new NullPointerException("url");
        String protocol = url.getProtocol();
        if ("file".equals(protocol)) {
            File file = FileURL.toFile(url, null);
            if (file.exists())
                return file.length();
            else
                return notExisted;
        }

        if ("jar".equals(protocol)) {
            Entry<ZipFile, ZipEntry> pair = openEntry(url);
            if (pair == null)
                return null;
            ZipFile zipFile = pair.getKey();
            ZipEntry zipEntry = pair.getValue();
            long size = zipEntry.getSize();
            zipFile.close();
            return size;
        }

        return null;
    }

    public static Long lastModified(URL url, Long notExisted)
            throws IOException {
        if (url == null)
            throw new NullPointerException("url");
        String protocol = url.getProtocol();
        if ("file".equals(protocol)) {
            File file = FileURL.toFile(url, null);
            if (file.exists())
                return file.lastModified();
            else
                return notExisted;
        }

        if ("jar".equals(protocol)) {
            Entry<ZipFile, ZipEntry> pair = openEntry(url);
            if (pair == null)
                return null;
            ZipFile zipFile = pair.getKey();
            ZipEntry zipEntry = pair.getValue();
            long time = zipEntry.getTime();
            zipFile.close();
            return time;
        }

        return null;
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

    public static Entry<ZipFile, ZipEntry> openEntry(URL zipURL)
            throws IOException {
        if (!"jar".equals(zipURL.getProtocol()))
            throw new IllegalArgumentException("Not a zip URL: " + zipURL);

        String path = zipURL.getPath(); // path = file:/...!...
        assert path.startsWith("file:");
        int excl = path.lastIndexOf('!');

        assert excl != -1;
        String zipFileName = path.substring(5, excl); // path = file:/...
        String entryName = path.substring(excl + 1);

        ZipFile zipFile = new ZipFile(zipFileName);
        try {
            ZipEntry zipEntry = zipFile.getEntry(entryName);
            if (zipEntry == null)
                return null;
            else
                return Pair.of(zipFile, zipEntry);
        } finally {
            zipFile.close();
        }
    }

}
