package net.bodz.bas.io.res;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.jar.JarFile;

import net.bodz.bas.c.loader.ClassResource;
import net.bodz.bas.err.UnexpectedException;

public class JarFiles {

    static JarFile jarFile(File file)
            throws IOException {
        return file == null ? null : new JarFile(file);
    }

    public static JarFile fromJarURL(URL url)
            throws IOException {
        return jarFile(_fromURL(url, true, false));
    }

    public static File _fromJarURL(URL url)
            throws IOException {
        return _fromURL(url, true, false);
    }

    public static JarFile fromURL(URL url, boolean jarProtocol, boolean fileProtocol)
            throws IOException {
        return jarFile(_fromURL(url, jarProtocol, fileProtocol));
    }

    public static File _fromURL(URL url, boolean jarProtocol, boolean fileProtocol)
            throws IOException {
        String protocol = url.getProtocol();
        switch (protocol) {
        case "jar":
            if (jarProtocol) {
                String path = url.getPath(); // file:....!/...
                int exclm = path.lastIndexOf('!');
                String jarPath = path.substring("file:".length(), exclm); // remove "file:"
                return new File(jarPath);
            }
            break;

        case "file":
            if (fileProtocol) {
                URI uri;
                try {
                    uri = url.toURI();
                } catch (URISyntaxException e) {
                    throw new UnexpectedException(e.getMessage(), e);
                }
                File file = new File(uri);
                String name = file.getName();
                if (name.endsWith(".jar"))
                    return file;
            }
            break;
        }

        return null;
    }

    /**
     * @return <code>null</code> if the class is run-time created, or not in a jar file
     *         (uncompressed).
     * @throws IOException
     */
    public static JarFile fromClass(Class<?> clazz)
            throws IOException {
        return jarFile(_fromClass(clazz));
    }

    /**
     * @return <code>null</code> if the class is run-time created, or not in a jar file
     *         (uncompressed).
     * @throws IOException
     */
    public static File _fromClass(Class<?> clazz)
            throws IOException {
        URL url = ClassResource.getClassBytesURL(clazz);
        if (url == null)
            return null;
        File jarFile = _fromJarURL(url);
        return jarFile;
    }

}
