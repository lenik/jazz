package net.bodz.bas.c.loader.scan;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.jar.JarFile;

import net.bodz.bas.c.loader.ClassResource;
import net.bodz.bas.err.UnexpectedException;

public class JarFiles {

    public static JarFile fromJarURL(URL url)
            throws IOException {
        return fromURL(url, true, false);
    }

    public static JarFile fromURL(URL url, boolean jarProtocol, boolean fileProtocol)
            throws IOException {
        if ("jar".equals(url.getProtocol())) {
            String path = url.getPath(); // "file:....!/..."
            int exclm = path.lastIndexOf('!');
            String jarPath = path.substring(5, exclm); // remove "file:"
            JarFile jarFile = new JarFile(jarPath);
            return jarFile;
        }

        if (fileProtocol) {
            if ("file".equals(url.getProtocol())) {
                URI uri;
                try {
                    uri = url.toURI();
                } catch (URISyntaxException e) {
                    throw new UnexpectedException(e.getMessage(), e);
                }
                File file = new File(uri);
                String name = file.getName();
                if (name.endsWith(".jar"))
                    return new JarFile(file);
            }
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
        URL url = ClassResource.getClassBytesURL(clazz);
        if (url == null)
            return null;
        JarFile jarFile = JarFiles.fromJarURL(url);
        if (jarFile == null)
            return null;
        return jarFile;
    }

}
