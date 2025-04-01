package net.bodz.bas.c.java.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map.Entry;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import net.bodz.bas.c.java.nio.file.FileFn;
import net.bodz.bas.err.UnexpectedException;
import net.bodz.bas.meta.decl.NotNull;
import net.bodz.bas.t.pojo.Pair;

public class FileURL {

    @NotNull
    public static URL toURL(@NotNull String pathname) {
        File file = new File(pathname);
        return toURL(file);
    }

    @NotNull
    public static URL toURL(@NotNull String pathname, URL fallback) {
        File file = new File(pathname);
        return toURL(file, fallback);
    }

    @NotNull
    public static URL toURL(@NotNull File file) {
        URI uri = file.toURI();
        try {
            return uri.toURL();
        } catch (MalformedURLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public static URL toURL(@NotNull File file, URL fallback) {
        URI uri = file.toURI();
        try {
            return uri.toURL();
        } catch (MalformedURLException e) {
            // throw new UnexpectedException("Can't convert File to URL", e);
            return fallback;
        }
    }

    @NotNull
    public static URL toURL(@NotNull Path path) {
        URI uri = path.toUri();
        try {
            return uri.toURL();
        } catch (MalformedURLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public static URL toURL(@NotNull Path path, URL fallback) {
        URI uri = path.toUri();
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
    public static Path toPath(@NotNull URL url, Path fallback) {
        try {
            URI uri = url.toURI();
            return Paths.get(uri);
        } catch (URISyntaxException e) {
            return fallback;
        }
    }

    /**
     * Convert the url to file when possible, otherwise return <code>null</code>.
     *
     * @return The corresponding file, or <code>null</code> if not available.
     */
    public static File toFile(@NotNull URL url, File fallback) {
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
    public static Path toNearestFilePath(@NotNull URL url) {
        File file = toNearestFile(url);
        return FileFn.toPath(file);
    }

    /**
     * If url is an entry of a jar file, then the jar file is returned.
     *
     * @return The nearest local file for the url. If no such file exists, returns <code>null</code>
     */
    public static File toNearestFile(@NotNull URL url) {
        String protocol = url.getProtocol();
        if ("file".equals(protocol))
            return new File(url.getFile());

        if ("jar".equals(protocol))
            return getJarFile(url);

        return null;
    }

    public static Path toNearestFilePath(@NotNull URL url, String removeSubPath, File fallback) {
        File file = toNearestFile(url, removeSubPath, fallback);
        return FileFn.toPath(file);
    }

    public static File toNearestFile(@NotNull URL url, String removeSubPath, File fallback) {
        if (removeSubPath == null || removeSubPath.isEmpty())
            return toFile(url, fallback);

        // jar:file:/C:/abc/dir/example.jar!/com/example/Name.class

        String s = url.toExternalForm();
        // s=file:/C:/abc/dir/example.jar!/com/example/Name.class
        if (!s.endsWith(removeSubPath))
            throw new IllegalArgumentException(String.format("URL isn't end with %s: %s", removeSubPath, url));

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
            truncatedURL = URI.create(s).toURL();
        } catch (MalformedURLException e) {
            throw new UnexpectedException("Truncated url became invalid: " + s, e);
        }
        File dir = FilePath.canoniOf(truncatedURL);
        return dir;
    }

    public static Long length(@NotNull URL url, Long notExisted)
            throws IOException {
        String protocol = url.getProtocol();
        if ("file".equals(protocol)) {
            File file = FileURL.toFile(url, null);
            if (file.exists())
                return file.length();
            else
                return notExisted;
        }

        if ("jar".equals(protocol)) {
            Entry<ZipFile, ZipEntry> pair;
            try {
                pair = openEntry(url);
            } catch (FileNotFoundException e) {
                return null;
            }
            ZipFile zipFile = pair.getKey();
            ZipEntry zipEntry = pair.getValue();
            long size = zipEntry.getSize();
            zipFile.close();
            return size;
        }

        return null;
    }

    public static Long lastModified(@NotNull URL url, Long notExisted)
            throws IOException {
        String protocol = url.getProtocol();
        if ("file".equals(protocol)) {
            File file = FileURL.toFile(url, null);
            if (file.exists())
                return file.lastModified();
            else
                return notExisted;
        }

        if ("jar".equals(protocol)) {
            Entry<ZipFile, ZipEntry> pair;
            try {
                pair = openEntry(url);
            } catch (FileNotFoundException e) {
                return null;
            }
            ZipFile zipFile = pair.getKey();
            ZipEntry zipEntry = pair.getValue();
            long time = zipEntry.getTime();
            zipFile.close();
            return time;
        }

        return null;
    }

    @NotNull
    static File getJarFile(@NotNull URL jarURL) {
        assert "jar".equals(jarURL.getProtocol());
        String s = jarURL.getPath(); // path = file:/...!...
        assert s.startsWith("file:");
        int excl = s.lastIndexOf('!');
        if (excl != -1) // assert
            s = s.substring(0, excl); // path = file:/...
        try {
            URL truncatedURL = URI.create(s).toURL();
            return FilePath.canoniOf(truncatedURL);
        } catch (MalformedURLException e) {
            throw new UnexpectedException(e);
        }
    }

    @NotNull
    public static Entry<ZipFile, ZipEntry> openEntry(@NotNull URL zipURL)
            throws IOException {
        if (!"jar".equals(zipURL.getProtocol()))
            throw new IllegalArgumentException("Not a zip URL: " + zipURL);

        String path = zipURL.getPath(); // path = file:/...!...
        assert path.startsWith("file:");
        int excl = path.lastIndexOf('!');

        assert excl != -1;
        String zipFileName = path.substring(5, excl); // path = file:/...
        String entryName = path.substring(excl + 1);

        try (ZipFile zipFile = new ZipFile(zipFileName)) {
            ZipEntry zipEntry = zipFile.getEntry(entryName);
            if (zipEntry == null)
                throw new FileNotFoundException(zipURL.toString());
            else
                return Pair.of(zipFile, zipEntry);
        }
    }

}
