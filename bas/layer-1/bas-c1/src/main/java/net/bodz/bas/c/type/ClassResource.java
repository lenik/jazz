package net.bodz.bas.c.type;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.nio.file.Path;
import java.util.jar.Manifest;

import net.bodz.bas.c.java.io.FileURL;
import net.bodz.bas.c.string.StringPart;
import net.bodz.bas.err.UnexpectedException;
import net.bodz.bas.meta.decl.NotNull;
import net.bodz.bas.meta.decl.Nullable;

public class ClassResource {

    /**
     * @return <code>null</code> if no manifest.
     */
    public static Manifest getManifest(Class<?> clazz) {
        URL url = clazz.getResource("/META-INF/MANIFEST.MF");
        if (url == null)
            return null;
        try (InputStream in = url.openStream()) {
            Manifest manifest = new Manifest(in);
            return manifest;
        } catch (IOException e) {
            throw new IllegalStateException("Bad manifest: " + clazz, e);
        }
    }

    public static Path getRootPath(Class<?> clazz) {
        URL rootURL = getRootURL(clazz);
        return FileURL.toNearestFilePath(rootURL);
    }

    public static File getRootFile(Class<?> clazz) {
        URL rootURL = getRootURL(clazz);
        return FileURL.toNearestFile(rootURL);
    }

    public static URL getRootURL(Class<?> clazz) {
        String classBytesName = clazz.getName().replace('.', '/') + ".class";

        ClassLoader loader = clazz.getClassLoader();
        if (loader == null)
            // throw new RuntimeException("No class loader bound for " + clazz);
            loader = ClassLoader.getSystemClassLoader();

        URL classBytesUrl = loader.getResource(classBytesName);
        if (classBytesUrl == null)
            throw new UnexpectedException("can't find class bytes for " + clazz);

        String rootUrl = classBytesUrl.toString();

        assert rootUrl.endsWith(classBytesName);
        rootUrl = rootUrl.substring(0, rootUrl.length() - classBytesName.length());

        if (rootUrl.endsWith("!/")) {
            // rootUrl = StringPart.chomp(rootUrl, "!/");
        } else {
            rootUrl = StringPart.chomp(rootUrl, "/");
            rootUrl = StringPart.chomp(rootUrl, "\\");
        }

        try {
            return URI.create(rootUrl).toURL();
        } catch (MalformedURLException e) {
            throw new UnexpectedException(e);
        }
    }

    public static URL getPackageURL(Class<?> clazz) {
        URL classBytes = getClassBytesURL(clazz);
        String classBytesUrl = classBytes.toString();

        int lastSlash = classBytesUrl.lastIndexOf('/');
        if (lastSlash == -1)
            throw new UnexpectedException("No slash found in: " + classBytesUrl);

        String packageUrl = classBytesUrl.substring(0, lastSlash);

        try {
            return URI.create(packageUrl).toURL();
        } catch (MalformedURLException e) {
            throw new UnexpectedException(e);
        }
    }

    /**
     * Same as {@link #getDataURLBySuffix(Class, String)} with ".class" as <code>extension</code>.
     */
    @NotNull
    public static URL getClassBytesURL(Class<?> clazz) {
        String name = clazz.getName(); // keep '$' as is.
        int lastDot = name.lastIndexOf('.');
        String base = lastDot == -1 ? name : name.substring(lastDot + 1);
        URL url = clazz.getResource(base + ".class");
        if (url == null)
            throw new UnexpectedException("class bytes not found for " + clazz);
        return url;
    }

    /**
     * @return <code>null</code> if the class bytes isn't located in a local file.
     */
    @Nullable
    public static Path getClassBytesLocalFile(Class<?> clazz) {
        URL url = getClassBytesURL(clazz);
        Path file = FileURL.toPath(url, null);
        return file;
    }

    public static URL getClassDirURL(Class<?> clazz) {
        String classBytesUrl = getClassBytesURL(clazz).toString();

        assert classBytesUrl.endsWith(".class");
        String classDirUrl = classBytesUrl.substring(0, classBytesUrl.length() - 6) + ".d";

        try {
            return URI.create(classDirUrl).toURL();
        } catch (MalformedURLException e) {
            throw new UnexpectedException(e.getMessage(), e);
        }
    }

    /**
     * @param extension
     *            don't include the dot(.)
     */
    public static URL getDataURL(Class<?> clazz, String extension) {
        return getDataURLBySuffix(clazz, "." + extension);
    }

    /**
     * @param suffix
     *            must include the dot(.), if necessary
     */
    public static URL getDataURLBySuffix(Class<?> clazz, String suffix) {
        String classSimpleName = clazz.getSimpleName();
        String resourceName = classSimpleName + suffix;
        return clazz.getResource(resourceName);
        // String spec = classSimpleName + suffix;
        // try {
        // URL url = new URL(context, spec);
        // return url;
        // } catch (MalformedURLException e) {
        // throw new IllegalArgumentException(e);
        // }
    }

}
