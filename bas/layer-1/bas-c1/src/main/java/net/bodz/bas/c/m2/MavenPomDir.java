package net.bodz.bas.c.m2;

import java.io.File;

import net.bodz.bas.c.java.io.FilePath;
import net.bodz.bas.c.string.StringPart;
import net.bodz.bas.c.type.ClassResource;
import net.bodz.bas.err.UnexpectedException;

public class MavenPomDir {

    private File pomFile;
    private File baseDir;

    public MavenPomDir(File baseDir) {
        if (baseDir == null)
            throw new NullPointerException("baseDir");
        this.baseDir = baseDir;
        this.pomFile = new File(baseDir, "pom.xml");
    }

    public String getName() {
        String name = baseDir.getName();
        return name;
    }

    public File getBaseDir() {
        return baseDir;
    }

    public File getPomFile() {
        return pomFile;
    }

    public File find(String name) {
        return new File(baseDir, name);
    }

    /**
     * Find the maven project directory from the specific class.
     * 
     * @param clazz
     *            The specific class.
     * @return <code>null</code> if the class bytes resource couldn't be found for the specific
     *         class.
     */
    public static MavenPomDir fromClass(Class<?> clazz) {
        String fname = clazz.getName().replace('.', '/') + ".class";

        File classFile = ClassResource.getClassBytesFile(clazz);
        if (classFile == null) {
            // It's just in the jar
            // throw new UnexpectedException("No class bytes for " + clazz);
            return null;
        }

        String path = classFile.getPath();
        path = FilePath.toUnixStyle(path);

        if (!path.endsWith(fname))
            throw new UnexpectedException("Unexpected class file: " + path);

        String dirname = path.substring(0, path.length() - fname.length() - 1);

        dirname = StringPart.rtrim(dirname, "target/classes");
        dirname = StringPart.rtrim(dirname, "target/test-classes");

        File dir = new File(dirname);

        return new MavenPomDir(dir);
    }

    public File getSourceFile(Class<?> clazz) {
        File classFile = ClassResource.getClassBytesFile(clazz);

        // foo/target/classes/ => foo/src/main/java/
        // foo/target/test-classes/ => foo/src/test/java/
        String path = classFile.getPath();
        path = FilePath.toUnixStyle(path);
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

    public File getSiblingResource(File dir) {
        String path = dir.getPath();
        path = FilePath.toUnixStyle(path);
        // src/main/java => src/main/resources
        // target/classes => src/main/resources
        // target/test-classes => src/test/resources
        path = path.replaceFirst("/src/main/java", "/src/main/resources");
        path = path.replaceFirst("/target/classes", "/src/main/resources");
        path = path.replaceFirst("/target/test-classes", "/src/test/resources");
        return new File(path);
    }

    /**
     * Find the source dir within the maven project directory from the specific class.
     * 
     * @param clazz
     *            The specific class.
     * @return <code>null</code> if the class bytes resource couldn't be found for the specific
     *         class.
     */
    public File getSourceDir(Class<?> clazz) {
        String path = _getSourceDirPath(clazz);
        return path == null ? null : new File(path);
    }

    private static String _getSourceDirPath(Class<?> clazz) {
        File classFile = ClassResource.getClassBytesFile(clazz);
        if (classFile == null)
            return null;

        // foo/target/classes/ => foo/src/main/java/
        // foo/target/test-classes/ => foo/src/test/java/
        String path = classFile.getPath();

        int pos = path.lastIndexOf("/target/classes/");
        if (pos != -1)
            return path.substring(0, pos) + "/src/main/java/";

        pos = path.lastIndexOf("/target/test-classes/");
        if (pos != -1)
            return path.substring(0, pos) + "/src/test/java/";

        return null;
    }

    public File getResourceDir(Class<?> clazz) {
        String path = _getResourceDirPath(clazz);
        return path == null ? null : new File(path);
    }

    private static String _getResourceDirPath(Class<?> clazz) {
        File classFile = ClassResource.getClassBytesFile(clazz);

        // foo/target/classes/ => foo/src/main/java/
        // foo/target/test-classes/ => foo/src/test/java/
        String path = classFile.getPath();

        int pos = path.lastIndexOf("/target/classes/");
        if (pos != -1)
            return path.substring(0, pos) + "/src/main/resources/";

        pos = path.lastIndexOf("/target/test-classes/");
        if (pos != -1)
            return path.substring(0, pos) + "/src/test/resources/";

        return null;
    }

}
