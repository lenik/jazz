package net.bodz.bas.files;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class FileInZip {

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

    public static IFolder toFolder(URL url, String removeSubPath)
            throws IOException {
        if (removeSubPath == null || removeSubPath.isEmpty())
            return new URLFile(url);
        // jar:file:/C:/abc/dir/example.jar!/com/example/Name.class
        // String _url = url.toExternalForm();

        // s=file:/C:/abc/dir/example.jar!/com/example/Name.class
        String s = url.toExternalForm();
        if (!s.endsWith(removeSubPath)) {
            throw new IllegalArgumentException(String.format("URL isn\'t end with %s: %s", removeSubPath, url));
        }
        int rlen = removeSubPath.length();
        // s=file:/C:/abc/dir/example.jar!/, or file:/C:/abc/dir/
        s = s.substring(0, s.length() - rlen);

        String protocol = url.getProtocol();
        if ("jar".equals(protocol)) {
            // the jar URL is verified by above. so now can safely return.
            File jarFile = getJarFile(url);
            IFile plainJarFile = new PlainFile(jarFile);
            return new ZippedFolder(plainJarFile);
        }
        URL truncatedURL = new URL(s);
        File dir = FilePath.canoniOf(truncatedURL);
        return new PlainFile(dir);
    }

}
