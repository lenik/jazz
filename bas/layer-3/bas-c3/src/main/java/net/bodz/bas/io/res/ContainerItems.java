package net.bodz.bas.io.res;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import net.bodz.bas.err.IllegalUsageError;

public class ContainerItems {

    public static IContainerItem findClass(Class<?> clazz)
            throws IOException {
        if (clazz == null)
            throw new NullPointerException("clazz");

        URL url = clazz.getResource(clazz.getSimpleName() + ".class");
        if (url == null)
            throw new IllegalUsageError("class bytes isn't found for " + clazz);

        String protocol = url.getProtocol();
        switch (protocol) {
        case "jar":
            return parseJarEntry(url);

        case "file":
            URI uri;
            try {
                uri = url.toURI();
            } catch (URISyntaxException e) {
                throw new IllegalArgumentException(e.getMessage(), e);
            }
            File file = new File(uri);
            String path = file.getPath();
            String className = clazz.getName();
            String relativePath = className.replace('.', '/') + ".class";
            assert path.endsWith(relativePath);
            String baseDir = path.substring(0, path.length() - relativePath.length() - 1);
            return new DirSubpathItem(new File(baseDir), relativePath);

        default:
            throw new UnsupportedOperationException();
        }
    }

    public static IContainerItem parseJarEntry(URL url)
            throws IOException {
        String path = url.getPath();
        int exclm = path.indexOf('!');
        String zipPath = path.substring("file:".length(), exclm);
        String entryName = path.substring(exclm + 1);
        JarFile jarFile = new JarFile(zipPath);
        JarEntry entry = jarFile.getJarEntry(entryName);
        return new JarEntryItem(jarFile, entry);
    }

}
