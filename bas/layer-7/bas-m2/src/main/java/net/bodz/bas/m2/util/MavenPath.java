package net.bodz.bas.m2.util;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class MavenPath {

    public static File getSourceFile(Class<?> clazz) {
        File classFile = getClassFile(clazz);

        // foo/target/classes/ => foo/src/main/java/
        // foo/target/test-classes/ => foo/src/test/java/
        String path = classFile.getPath();
        path = normalizePathString(path);
        path = path.replace("/target/classes/", "/src/main/java/");
        path = path.replace("/target/test-classes/", "/src/test/java/");

        // strip ".class"
        int lastDot = path.lastIndexOf('.');
        if (lastDot != -1)
            path = path.substring(0, lastDot);

        // strip inner classes.
        int dollar = path.indexOf('$');
        if (dollar != -1)
            path = path.substring(0, dollar);

        path += ".java";

        return new File(path);
    }

    public static File getClassFile(Class<?> clazz) {
        String resName = clazz.getName().replace('.', '/') + ".class";
        URL url = clazz.getClassLoader().getResource(resName);
        URI uri;
        try {
            uri = url.toURI();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
        File file = new File(uri);
        return file;
    }

    public static File getSiblingResource(File dir) {
        String path = dir.getPath();
        path = normalizePathString(path);
        // src/main/java => src/main/resources
        // target/classes => src/main/resources
        // target/test-classes => src/test/resources
        path = path.replaceFirst("/src/main/java", "/src/main/resources");
        path = path.replaceFirst("/target/classes", "/src/main/resources");
        path = path.replaceFirst("/target/test-classes", "/src/test/resources");
        return new File(path);
    }

    static final String fileSeparator = System.getProperty("file.separator");

    static String normalizePathString(String path) {
        if (path == null)
            return null;
        // if (SystemProperties.getOsName().equals("win32"))
        path = path.replace(fileSeparator, "/");
        // Mac? path = path.replace(":", SLASH);
        return path;
    }

    public static File getResourceDir(Class<?> clazz) {
        File classFile = getClassFile(clazz);

        // foo/target/classes/ => foo/src/main/java/
        // foo/target/test-classes/ => foo/src/test/java/
        String path = classFile.getPath();

        int i;
        do {
            i = path.indexOf("/target/classes/");
            if (i != -1) {
                path = path.substring(0, i) + "/src/main/resources/";
                break;
            }

            i = path.indexOf("/target/test-classes/");
            if (i != -1) {
                path = path.substring(0, i) + "/src/test/resources/";
                break;
            }
        } while (false);

        return new File(path);
    }

}
