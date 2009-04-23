package net.bodz.bas.io;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.jar.JarFile;
import java.util.zip.ZipFile;

public class URLResLink extends _ResLink {

    private URL url;

    public URLResLink(URL url) {
        if (url == null)
            throw new NullPointerException("url");
        this.url = url;
    }

    @Override
    public File getFile() {
        try {
            return Files.getFile(url);
        } catch (MalformedURLException e) {
            return null;
        }
    }

    @Override
    public URL getURL() {
        return url;
    }

    @Override
    public Boolean exists() {
        return false;
    }

    @Override
    public InputStream openInputStream() throws IOException {
        return url.openStream();
    }

    @Override
    public OutputStream openOutputStream(boolean append) throws IOException {
        throw new UnsupportedOperationException("URL resource is read-only");
    }

    /**
     * XXX - can't open jar file from URL
     */
    @Override
    public JarFile openJarFile() throws IOException {
        File file = Files.convertToFile(url, url);
        return new JarFile(file);
    }

    /**
     * XXX - can't open zip file from URL
     */
    @Override
    public ZipFile openZipFile() throws IOException {
        File file = Files.convertToFile(url, url);
        return new ZipFile(file);
    }

    @Override
    public int hashCode() {
        return 0xb33cf090 ^ url.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof URLResLink) {
            URLResLink a = (URLResLink) obj;
            return url.equals(a.url);
        }
        return false;
    }

    @Override
    public String toString() {
        return url.toString();
    }

}
